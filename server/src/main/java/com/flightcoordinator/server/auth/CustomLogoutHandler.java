package com.flightcoordinator.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.flightcoordinator.server.token.TokenEntity;
import com.flightcoordinator.server.token.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutHandler {

  @Autowired
  private TokenRepository tokenRepository;

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) {
    String authHeader = request.getHeader("Authorization");
    String authToken;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      authToken = authHeader.substring(7);
      TokenEntity savedToken = tokenRepository.findByToken(authToken).orElse(null);

      if (savedToken != null) {
        savedToken.setExpired(true);
        savedToken.setRevoked(true);
        tokenRepository.save(savedToken);

        SecurityContextHolder.clearContext();
      }
    }
  }
}
