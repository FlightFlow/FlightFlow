package com.flightcoordinator.server.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.flightcoordinator.server.enums.PermissionsPerResource;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "system_role_table")
public class SystemRoleEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "role_name", nullable = false)
  private String roleName;

  @ElementCollection
  @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
  @MapKeyEnumerated(EnumType.STRING)
  @Column(name = "permission")
  @Enumerated(EnumType.STRING)
  private List<PermissionsPerResource> permissionPerResource;

  // One-to-many relationship with UserEntity
  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<UserEntity> assignedUsers;

  public SystemRoleEntity() {
  }

  public SystemRoleEntity(String id, String roleName, List<PermissionsPerResource> permissionPerResource,
      Set<UserEntity> assignedUsers) {
    this.id = id;
    this.roleName = roleName;
    this.permissionPerResource = permissionPerResource;
    this.assignedUsers = assignedUsers;
  }

  // Getters and Setters
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

  public List<PermissionsPerResource> getPermissionPerResource() {
    return permissionPerResource;
  }

  public void setPermissionPerResource(List<PermissionsPerResource> permissionPerResource) {
    this.permissionPerResource = permissionPerResource;
  }

  public Set<UserEntity> getAssignedUsers() {
    return assignedUsers;
  }

  public void setAssignedUsers(Set<UserEntity> assignedUsers) {
    this.assignedUsers = assignedUsers;
  }
}
