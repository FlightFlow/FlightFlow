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
import com.flightcoordinator.server.dto.LoginRequestDTO;
import com.flightcoordinator.server.dto.LoginResponseDTO;
import com.flightcoordinator.server.dto.RegisterRequestDTO;
import com.flightcoordinator.server.entity.UserEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.UserRepository;

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

  public void register(RegisterRequestDTO registerRequestDetails) {
    Optional<UserEntity> existingUser = userRepository.findByEmail(registerRequestDetails.getEmail());

    if (existingUser != null) {
      throw new AppError("This e-mail is already registered.", HttpStatus.CONFLICT.value());
    }

    if (!registerRequestDetails.getPassword().equals(registerRequestDetails.getPasswordAgain())) {
      throw new AppError(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }

    UserEntity newUser = new UserEntity();
    newUser.setFullName(registerRequestDetails.getFullName());
    newUser.setEmail(registerRequestDetails.getEmail());
    newUser.setPassword(encoder.encode(registerRequestDetails.getPassword()));

    userRepository.save(newUser);
  }

  public LoginResponseDTO login(LoginRequestDTO loginRequestDetails) {
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

    LoginResponseDTO authDetails = new LoginResponseDTO();
    authDetails.setAccessToken(jwtService.generateAccessToken(user.getEmail()));
    authDetails.setRefreshToken(jwtService.generateRefreshToken(user.getEmail()));

    return authDetails;
  }
}
