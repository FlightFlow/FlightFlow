package com.flightcoordinator.server.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
  Optional<TokenEntity> findByToken(String token);
}
