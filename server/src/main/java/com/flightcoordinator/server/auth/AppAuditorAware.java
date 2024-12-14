package com.flightcoordinator.server.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.flightcoordinator.server.entity.UserEntity;

public class AppAuditorAware implements AuditorAware<String> {
  // TODO use
  @SuppressWarnings("null")
  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null ||
        !authentication.isAuthenticated() ||
        authentication instanceof AnonymousAuthenticationToken) {
      return Optional.empty();
    }

    UserEntity userPrincipal = (UserEntity) authentication.getPrincipal();
    return Optional.ofNullable(userPrincipal.getId());
  }
}
