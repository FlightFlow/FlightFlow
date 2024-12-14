package com.flightcoordinator.server.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.UserEntity;

@Service
public class TokenService {
  @Autowired
  private TokenRepository tokenRepository;

  public void revokeSingleToken(String token) {
    TokenEntity savedToken = tokenRepository.findByToken(token).orElse(null);

    if (savedToken != null) {
      savedToken.setExpired(true);
      savedToken.setRevoked(true);
      tokenRepository.save(savedToken);

      SecurityContextHolder.clearContext();
    }
  }

  public void revokeAllTokensForUser(UserEntity user) {
    List<TokenEntity> userTokens = tokenRepository.findByAssociatedUser(user.getId());

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
}
