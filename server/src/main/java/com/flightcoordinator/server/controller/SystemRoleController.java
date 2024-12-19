package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.entity.SystemRoleEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.SystemRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/system-role")
@Tag(name = "System Roles' Controller", description = "Endpoints for managing roles for system users.")
public class SystemRoleController {
  @Autowired
  private SystemRoleService systemRoleService;

  @PostMapping("/getAll")
  @PreAuthorize("hasAuthority('SYS_ROLE_READ')")
  @Operation(summary = "Get all the systemRoles", description = "Retrieve the details of all systemRoles.")
  public ResponseEntity<ResponseObject<List<SystemRoleEntity>>> getAllSystemRoles() {
    List<SystemRoleEntity> systemRoles = systemRoleService.getAllSystemRoles();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), systemRoles);
  }

  @PostMapping("/getById")
  @PreAuthorize("hasAuthority('SYS_ROLE_READ')")
  @Operation(summary = "Get a systemRole by id", description = "Retrieve the details of a spesific systemRole using it's ID.")
  public ResponseEntity<ResponseObject<SystemRoleEntity>> getSystemRoleById(@RequestBody String systemRoleId) {
    SystemRoleEntity systemRole = systemRoleService.getSingleSystemRoleById(systemRoleId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), systemRole);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('SYS_ROLE_CREATE')")
  @Operation(summary = "Create a new systemRole", description = "Create a new systemRole.")
  public ResponseEntity<ResponseObject<Object>> createSystemRole(@RequestBody SystemRoleEntity systemRole) {
    systemRoleService.createSystemRole(systemRole);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PatchMapping("/update")
  @PreAuthorize("hasAuthority('SYS_ROLE_UPDATE')")
  @Operation(summary = "Update a systemRole", description = "Update an existing systemRole.")
  public ResponseEntity<ResponseObject<Object>> updateSystemRole(@RequestBody String systemRoleId,
      @RequestBody SystemRoleEntity systemRole) {
    systemRoleService.updateSystemRole(systemRoleId, systemRole);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("/delete")
  @PreAuthorize("hasAuthority('SYS_ROLE_DELETE')")
  @Operation(summary = "Delete a systemRole", description = "Delete an existing systemRole.")
  public ResponseEntity<ResponseObject<Object>> deleteSystemRole(@RequestBody String systemRoleId) {
    systemRoleService.deleteSystemRole(systemRoleId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
