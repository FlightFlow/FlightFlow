package com.flightcoordinator.server.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.flightcoordinator.server.exception.AppError;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private JWTService jwtService;

  @Autowired
  ApplicationContext applicationContext;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String authToken = null;
    String emailAsUsername = null;

    if (authHeader != null && authHeader.startsWith("Bearer")) {
      authToken = authHeader.substring(7);
      emailAsUsername = jwtService.extractUsername(authToken);
    }

    if (emailAsUsername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = applicationContext
          .getBean(CustomUserDetailsService.class)
          .loadUserByUsername(emailAsUsername);

      if (jwtService.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      } else {
        throw new AppError("Expired Token", HttpStatus.UNAUTHORIZED.value());
      }
    }
    filterChain.doFilter(request, response);
  }
}
