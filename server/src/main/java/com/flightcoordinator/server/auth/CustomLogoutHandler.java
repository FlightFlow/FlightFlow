package com.flightcoordinator.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.auth.token.TokenService;
import com.flightcoordinator.server.entity.UserEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomLogoutHandler implements LogoutHandler {
  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) {
    String authHeader = request.getHeader("Authorization");
    String authToken;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      authToken = authHeader.substring(7);
      String userEmailAsUsername = tokenService.getEmailAsUsernameFromToken(authToken);
      UserEntity user = userRepository.findByEmail(userEmailAsUsername).orElse(null);

      if (user != null) {
        tokenService.revokeAllRefreshTokensForUser(user);

        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, authentication);

        SecurityContextHolder.clearContext();
      } else {
        throw new AppError(
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            HttpStatus.INTERNAL_SERVER_ERROR.value());
      }
    }
  }
}
