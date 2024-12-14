package com.flightcoordinator.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.flightcoordinator.server.entity.UserEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.UserRepository;
import com.flightcoordinator.server.token.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutHandler {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JWTService jwtService;

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) {
    String authHeader = request.getHeader("Authorization");
    String authToken;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      authToken = authHeader.substring(7);
      String userEmailAsUsername = jwtService.extractUsername(authToken);
      UserEntity user = userRepository.findByEmail(userEmailAsUsername).orElse(null);

      if (user != null) {
        tokenService.revokeSingleToken(authToken);
        tokenService.revokeAllTokensForUser(user);

        SecurityContextHolder.clearContext();
      } else {
        throw new AppError(
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            HttpStatus.INTERNAL_SERVER_ERROR.value());
      }
    }
  }
}
