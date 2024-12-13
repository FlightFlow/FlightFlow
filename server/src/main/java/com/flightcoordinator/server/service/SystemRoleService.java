package com.flightcoordinator.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.SystemRoleEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.SystemRoleRepository;

@Service
public class SystemRoleService {
  @Autowired
  private SystemRoleRepository systemRoleRepository;

  public List<SystemRoleEntity> getAllRoles() {
    List<SystemRoleEntity> roles = systemRoleRepository.findAll();
    if (roles.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return roles;
  }
}
