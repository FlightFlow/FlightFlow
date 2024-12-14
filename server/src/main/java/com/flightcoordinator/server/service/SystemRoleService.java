package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

  public SystemRoleEntity getSingleSystemRoleById(String systemRoleId) {
    Optional<SystemRoleEntity> systemRole = systemRoleRepository.findById(systemRoleId);
    return systemRole.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<SystemRoleEntity> getMultipleSystemRolesById(List<String> systemRoleIds) {
    List<SystemRoleEntity> roles = systemRoleRepository.findAllById(systemRoleIds);
    if (roles.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return roles;
  }

  public List<SystemRoleEntity> getAllSystemRoles() {
    List<SystemRoleEntity> roles = systemRoleRepository.findAll();
    if (roles.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return roles;
  }

  public void createSystemRole(SystemRoleEntity newSystemRole) {
    systemRoleRepository.save(newSystemRole);
  }

  public void updateSystemRole(String systemRoleId, SystemRoleEntity updatedSystemRole) {
    SystemRoleEntity existingSystemRole = getSingleSystemRoleById(systemRoleId);

    existingSystemRole.setRoleName(updatedSystemRole.getRoleName());
    existingSystemRole.setPermissionPerResource(updatedSystemRole.getPermissionPerResource());

    systemRoleRepository.save(existingSystemRole);
  }

  public void deleteSystemRole(String systemRoleId) {
    SystemRoleEntity existingSystemRole = getSingleSystemRoleById(systemRoleId);
    systemRoleRepository.delete(existingSystemRole);
  }

  public Boolean doesSingleSystemRoleExist(SystemRoleEntity systemRole) {
    String systemRoleId = systemRole.getId();
    Optional<SystemRoleEntity> systemRoleFound = systemRoleRepository.findById(systemRoleId);
    return systemRoleFound.isPresent();
  }

  public Boolean doesMultipleSystemRolesExist(List<SystemRoleEntity> systemRoles) {
    List<String> systemRoleIds = new ArrayList<>();
    systemRoles.forEach(systemRole -> systemRoleIds.add(systemRole.getId()));
    List<SystemRoleEntity> systemRolesFound = systemRoleRepository.findAllById(systemRoleIds);
    if (systemRoles.size() != systemRolesFound.size()) {
      return false;
    }
    return systemRolesFound.isEmpty();
  }
}
