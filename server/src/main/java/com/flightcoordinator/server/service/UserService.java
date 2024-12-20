package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.flightcoordinator.server.entity.SystemRoleEntity;
import com.flightcoordinator.server.entity.UserEntity;
import com.flightcoordinator.server.enums.PermissionsPerResource;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.SystemRoleRepository;
import com.flightcoordinator.server.repository.UserRepository;

import jakarta.servlet.http.Cookie;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SystemRoleRepository systemRoleRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public void register(RegisterDetailsDTO registerDetails) {
    Optional<UserEntity> existingUser = userRepository.findByUsername(registerDetails.getUsername());

    if (existingUser.isPresent()) {
      throw new AppError("auth.exception.userExists", HttpStatus.CONFLICT.value());
    }

    if (!registerDetails.getPassword().equals(registerDetails.getPasswordAgain())) {
      throw new AppError("auth.exception.passwordsDoNotMatch", HttpStatus.BAD_REQUEST.value());
    }

    // TEMP START
    List<PermissionsPerResource> resourcePermissions = new ArrayList<>();
    resourcePermissions.addAll(Arrays.asList(PermissionsPerResource.values()));

    SystemRoleEntity devRole = new SystemRoleEntity();
    devRole.setRoleName("Developer");
    devRole.setPermissionPerResource(resourcePermissions);

    systemRoleRepository.save(devRole);
    // TEMP END

    UserEntity newUser = new UserEntity();
    newUser.setFullName(registerDetails.getFullName());
    newUser.setUsername(registerDetails.getUsername());
    newUser.setEmail(registerDetails.getEmail());
    newUser.setPassword(passwordEncoder.encode(registerDetails.getPassword()));
    newUser.setRole(devRole);
    newUser.setIsActive(true);
    newUser.setIsLocked(false);

    userRepository.save(newUser);
  }

  public AuthDetailsDTO login(LoginDetailsDTO loginRequestDetails) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequestDetails.getUsername(),
            loginRequestDetails.getPassword()));

    if (!authentication.isAuthenticated()) {
      throw new AppError("auth.exception.cannotAuthenticate", HttpStatus.BAD_REQUEST.value());
    }

    UserEntity user = userRepository.findByUsername(loginRequestDetails.getUsername())
        .orElseThrow(() -> new AppError("auth.exception.cannotFindUser", HttpStatus.NOT_FOUND.value()));

    AuthDetailsDTO newAuthDetails = tokenService.generateAuthDetails(user);
    return newAuthDetails;
  }

  public Cookie getNewAccessTokenAsCookie(String accessToken, String refreshToken) {
    String username = tokenService.getUsernameFromToken(refreshToken);
    UserEntity user = userRepository.findByUsername(username)
        .orElseThrow(() -> new AppError("auth.exception.invalidToken", HttpStatus.UNAUTHORIZED.value()));

    return tokenService.getNewAccessTokenAsCookie(user, accessToken, refreshToken);
  }

  public void validate(String accessToken, String refreshToken) {
    String usernameFromRefreshToken = tokenService.getUsernameFromToken(refreshToken);
    userRepository.findByUsername(usernameFromRefreshToken)
        .orElseThrow(() -> new AppError("auth.exception.invalidToken", HttpStatus.UNAUTHORIZED.value()));

    String usernameFromAccessToken = tokenService.getUsernameFromToken(refreshToken);
    userRepository.findByUsername(usernameFromAccessToken)
        .orElseThrow(() -> new AppError("auth.exception.invalidToken", HttpStatus.UNAUTHORIZED.value()));
  }

  public UserEntity getCurrentUserDetails(String accessToken) {
    String username = tokenService.getUsernameFromToken(accessToken);
    UserEntity currentUser = userRepository.findByUsername(username)
        .orElseThrow(() -> new AppError("auth.exception.invalidToken", HttpStatus.NOT_FOUND.value()));

    return currentUser;
  }

  public List<UserEntity> getAllUsers() {
    List<UserEntity> users = userRepository.findAll();
    if (users.isEmpty()) {
      throw new AppError("auth.exception.cannotFindUser", HttpStatus.NOT_FOUND.value());
    }
    return users;
  }

  public void deleteUser(String accessToken, String refreshToken) {

  }
}
