package com.flightcoordinator.server.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
  @Autowired
  private TokenRepository tokenRepository;
}
