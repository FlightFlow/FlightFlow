package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.auth.token.TokenService;
import com.flightcoordinator.server.dto.AuthDetailsDTO;
import com.flightcoordinator.server.dto.LoginDetailsDTO;
import com.flightcoordinator.server.dto.RegisterDetailsDTO;
import com.flightcoordinator.server.entity.UserEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.UserRepository;

import jakarta.servlet.http.Cookie;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public void register(RegisterDetailsDTO registerDetails) {
    Optional<UserEntity> existingUser = userRepository.findByEmail(registerDetails.getEmail());

    if (existingUser != null) {
      throw new AppError("This e-mail is already registered.", HttpStatus.CONFLICT.value());
    }

    if (!registerDetails.getPassword().equals(registerDetails.getPasswordAgain())) {
      throw new AppError(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value());
    }

    UserEntity newUser = new UserEntity();
    newUser.setFullName(registerDetails.getFullName());
    newUser.setEmail(registerDetails.getEmail());
    newUser.setPassword(passwordEncoder.encode(registerDetails.getPassword()));

    userRepository.save(newUser);
  }

  public AuthDetailsDTO login(LoginDetailsDTO loginRequestDetails) {
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

    AuthDetailsDTO newAuthDetails = tokenService.generateAuthDetails(user);
    return newAuthDetails;
  }

  public Cookie getNewAccessTokenAsCookie(String accessToken, String refreshToken) {
    String userEmail = tokenService.getEmailAsUsernameFromToken(refreshToken);
    UserEntity user = userRepository.findByEmail(userEmail).orElse(null);
    if (user == null) {
      throw new AppError(
          HttpStatus.UNAUTHORIZED.getReasonPhrase(),
          HttpStatus.UNAUTHORIZED.value());
    }

    return tokenService.getNewAccessTokenAsCookie(user, accessToken, refreshToken);
  }

  public UserEntity getCurrentUserDetails(String accessToken) {
    String userEmail = tokenService.getEmailAsUsernameFromToken(accessToken);
    UserEntity currentUser = userRepository.findByEmail(userEmail).orElse(null);
    if (currentUser == null) {
      throw new AppError(
          HttpStatus.NOT_FOUND.getReasonPhrase(),
          HttpStatus.NOT_FOUND.value());
    }
    return currentUser;
  }

  public List<UserEntity> getAllUsers() {
    List<UserEntity> users = userRepository.findAll();
    if (users.isEmpty()) {
      throw new AppError(
          HttpStatus.NOT_FOUND.getReasonPhrase(),
          HttpStatus.NOT_FOUND.value());
    }
    return users;
  }

  public void deleteUser(String accessToken, String refreshToken) {

  }
}
