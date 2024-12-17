package com.flightcoordinator.server.auth.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.auth.JWTService;
import com.flightcoordinator.server.dto.AuthDetailsDTO;
import com.flightcoordinator.server.entity.UserEntity;
import com.flightcoordinator.server.exception.AppError;

import jakarta.servlet.http.Cookie;

@Service
public class TokenService {
  @Autowired
  private JWTService jwtService;

  @Autowired
  private TokenRepository tokenRepository;

  public AuthDetailsDTO generateAuthDetails(UserEntity user) {
    String accessToken = jwtService.generateAccessToken(user.getEmail());
    String refreshToken = jwtService.generateRefreshToken(user.getEmail());

    AuthDetailsDTO authDetails = new AuthDetailsDTO();

    Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
    accessTokenCookie.setHttpOnly(true);
    accessTokenCookie.setSecure(true);
    accessTokenCookie.setPath("/");

    authDetails.setAccessTokenCookie(accessTokenCookie);

    Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
    refreshTokenCookie.setHttpOnly(true);
    refreshTokenCookie.setSecure(true);
    refreshTokenCookie.setPath("/api/v1/auth/refresh-token");

    authDetails.setRefreshTokenCookie(refreshTokenCookie);

    return authDetails;
  }

  public void revokeSingleRefreshToken(String token) {
    TokenEntity savedToken = tokenRepository.findByToken(token).orElse(null);

    if (savedToken != null) {
      savedToken.setExpired(true);
      savedToken.setRevoked(true);
      tokenRepository.save(savedToken);

      SecurityContextHolder.clearContext();
    }
  }

  public void revokeAllRefreshTokensForUser(UserEntity user) {
    List<TokenEntity> userTokens = tokenRepository.findByAssociatedUser(user);

    if (!userTokens.isEmpty()) {
      userTokens.forEach(token -> {
        token.setExpired(true);
        token.setRevoked(true);
      });
    }

    tokenRepository.saveAll(userTokens);
  }

  public void saveRefreshToken(UserEntity associatedUser, String token) {
    TokenEntity newToken = new TokenEntity();

    newToken.setAssociatedUser(associatedUser);
    newToken.setExpired(false);
    newToken.setRevoked(false);
    newToken.setToken(token);

    tokenRepository.save(newToken);
  }

  public Cookie getNewAccessTokenAsCookie(UserEntity user, String accessToken, String refreshToken) {
    Boolean isRefreshTokenValid = jwtService.validateToken(refreshToken, user);
    if (!isRefreshTokenValid) {
      throw new AppError(
          HttpStatus.UNAUTHORIZED.getReasonPhrase(),
          HttpStatus.UNAUTHORIZED.value());
    }

    String emailFromAccessToken = jwtService.extractUsername(accessToken);
    if (!emailFromAccessToken.equals(user.getEmail())) {
      throw new AppError(
          HttpStatus.UNAUTHORIZED.getReasonPhrase(),
          HttpStatus.UNAUTHORIZED.value());
    }

    Boolean isAccessTokenExpired = jwtService.isTokenExpired(accessToken);
    if (!isAccessTokenExpired) {
      throw new AppError(
          HttpStatus.CONFLICT.getReasonPhrase(),
          HttpStatus.CONFLICT.value());
    }

    revokeAllRefreshTokensForUser(user);

    String newAccessToken = jwtService.generateAccessToken(user.getEmail());
    saveRefreshToken(user, newAccessToken);

    Cookie accessTokenAsCookie = new Cookie("accessToken", newAccessToken);
    accessTokenAsCookie.setHttpOnly(true);
    accessTokenAsCookie.setSecure(true);
    accessTokenAsCookie.setPath("/");

    return accessTokenAsCookie;
  }

  public String getEmailAsUsernameFromToken(String token) {
    return jwtService.extractUsername(token);
  }
}
