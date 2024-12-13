package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_role_table")
public class SystemRoleEntity {
  public enum SystemPermission {
    CREATE,
    READ,
    UPDATE,
    DELETE;
  }

  public enum SystemResource {
    AIRPORT,
    ALGO_RESULT,
    ALGO_RUN,
    CERT,
    CREW,
    PLANE,
    ROUTE,
    RUNWAY,
    SYS_ROLE,
    USER,
    VEHICLE;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "role_name", nullable = false)
  private String roleName;

  @Column(name = "permission_per_resource", nullable = false)
  private Map<SystemResource, List<SystemPermission>> permissionPerResource;

  public SystemRoleEntity() {
  }

  public SystemRoleEntity(String id, String roleName,
      Map<SystemResource, List<SystemPermission>> permissionPerResource) {
    this.id = id;
    this.roleName = roleName;
    this.permissionPerResource = permissionPerResource;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Map<SystemResource, List<SystemPermission>> getPermissionPerResource() {
    return permissionPerResource;
  }

  public void setPermissionPerResource(Map<SystemResource, List<SystemPermission>> permissionPerResource) {
    this.permissionPerResource = permissionPerResource;
  }

  public List<String> getAllPermissions() {
    List<String> allPermissionsAsList = new ArrayList<>();

    for (Map.Entry<SystemResource, List<SystemPermission>> entry : permissionPerResource.entrySet()) {
      SystemResource resource = entry.getKey();
      List<SystemPermission> permissions = entry.getValue();

      for (SystemPermission permission : permissions) {
        allPermissionsAsList.add(resource.name() + "_" + permission.name());
      }
    }
    return allPermissionsAsList;
  }
}
