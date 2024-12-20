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
    String accessToken = jwtService.generateAccessToken(user.getUsername());
    String refreshToken = jwtService.generateRefreshToken(user.getUsername());

    AuthDetailsDTO authDetails = new AuthDetailsDTO();

    Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
    // accessTokenCookie.setHttpOnly(true);
    // accessTokenCookie.setSecure(true);
    accessTokenCookie.setDomain("localhost");
    accessTokenCookie.setPath("/");

    authDetails.setAccessTokenCookie(accessTokenCookie);

    Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
    // refreshTokenCookie.setHttpOnly(true);
    // refreshTokenCookie.setSecure(true);
    refreshTokenCookie.setDomain("localhost");
    refreshTokenCookie.setPath("/api/v1/auth/newRefreshToken");

    authDetails.setRefreshTokenCookie(refreshTokenCookie);

    saveRefreshToken(user, refreshToken);

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

    newToken.setToken(token);
    newToken.setAssociatedUser(associatedUser);
    newToken.setExpired(false);
    newToken.setRevoked(false);

    tokenRepository.save(newToken);
  }

  public Cookie getNewAccessTokenAsCookie(UserEntity user, String accessToken, String refreshToken) {
    Boolean isRefreshTokenValid = jwtService.validateToken(refreshToken, user);
    if (!isRefreshTokenValid) {
      throw new AppError(
          "authException.invalidToken",
          HttpStatus.UNAUTHORIZED.value());
    }

    String usernameFromAccessToken = jwtService.extractUsername(accessToken);
    if (!usernameFromAccessToken.equals(user.getUsername())) {
      throw new AppError(
          "authException.invalidToken",
          HttpStatus.UNAUTHORIZED.value());
    }

    Boolean isAccessTokenExpired = jwtService.isTokenExpired(accessToken);
    if (!isAccessTokenExpired) {
      throw new AppError(
          "authException.expiredToken",
          HttpStatus.CONFLICT.value());
    }

    revokeAllRefreshTokensForUser(user);

    String newAccessToken = jwtService.generateAccessToken(user.getUsername());
    saveRefreshToken(user, newAccessToken);

    Cookie accessTokenAsCookie = new Cookie("accessToken", newAccessToken);
    accessTokenAsCookie.setHttpOnly(true);
    accessTokenAsCookie.setSecure(true);
    accessTokenAsCookie.setPath("/");

    return accessTokenAsCookie;
  }

  public String getUsernameFromToken(String token) {
    return jwtService.extractUsername(token);
  }
}
