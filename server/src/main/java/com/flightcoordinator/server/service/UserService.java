package com.flightcoordinator.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.auth.JWTService;
import com.flightcoordinator.server.dto.AuthDetailsDTO;
import com.flightcoordinator.server.dto.LoginDetailsDTO;
import com.flightcoordinator.server.dto.RegisterDetailsDTO;
import com.flightcoordinator.server.entity.UserEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.UserRepository;

import jakarta.servlet.http.Cookie;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private BCryptPasswordEncoder encoder;

  public void register(RegisterDetailsDTO registerDetails) {
    Optional<UserEntity> existingUser = userRepository.findByEmail(registerDetails.getEmail());

    if (existingUser != null) {
      throw new AppError("This e-mail is already registered.", HttpStatus.CONFLICT.value());
    }

    if (!registerDetails.getPassword().equals(registerDetails.getPasswordAgain())) {
      throw new AppError(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }

    UserEntity newUser = new UserEntity();
    newUser.setFullName(registerDetails.getFullName());
    newUser.setEmail(registerDetails.getEmail());
    newUser.setPassword(encoder.encode(registerDetails.getPassword()));

    userRepository.save(newUser);
  }

  public AuthDetailsDTO login(LoginDetailsDTO loginRequestDetails) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequestDetails.getUsername(),
            loginRequestDetails.getPassword()));

    if (!authentication.isAuthenticated()) {
      throw new AppError(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }

    UserEntity user = userRepository.findByEmail(loginRequestDetails.getEmail())
        .orElseThrow(() -> new AppError(
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            HttpStatus.INTERNAL_SERVER_ERROR.value()));

    AuthDetailsDTO authDetails = new AuthDetailsDTO();

    Cookie accessTokenCookie = new Cookie("accessToken", jwtService.generateAccessToken(user.getEmail()));
    accessTokenCookie.setHttpOnly(true);
    accessTokenCookie.setSecure(true);
    accessTokenCookie.setPath("/");

    authDetails.setAccessTokenCookie(accessTokenCookie);

    Cookie refreshTokenCookie = new Cookie("refreshToken", jwtService.generateRefreshToken(user.getEmail()));
    refreshTokenCookie.setHttpOnly(true);
    refreshTokenCookie.setSecure(true);
    refreshTokenCookie.setPath("/api/v1/auth/refresh-token");

    authDetails.setRefreshTokenCookie(refreshTokenCookie);

    return authDetails;
  }

  public Cookie getNewAccessToken(String accessToken, String refreshToken) {
    UserEntity user = userRepository.findByEmail(jwtService.extractUsername(refreshToken)).orElse(null);
    if (user == null) {
      throw new AppError(
          HttpStatus.UNAUTHORIZED.getReasonPhrase(),
          HttpStatus.UNAUTHORIZED.value());
    }

    Boolean isTokenValid = jwtService.validateToken(accessToken, user);
    if (!isTokenValid) {
      throw new AppError(
          HttpStatus.UNAUTHORIZED.getReasonPhrase(),
          HttpStatus.UNAUTHORIZED.value());
    }

    Cookie accessTokenCookie = new Cookie("accessToken", jwtService.generateAccessToken(user.getEmail()));
    accessTokenCookie.setHttpOnly(true);
    accessTokenCookie.setSecure(true);
    accessTokenCookie.setPath("/");

    return accessTokenCookie;
  }
}
