package com.flightcoordinator.server.auth.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
  Optional<TokenEntity> findByToken(String token);

  List<TokenEntity> findByAssociatedUser(String userId);
}
