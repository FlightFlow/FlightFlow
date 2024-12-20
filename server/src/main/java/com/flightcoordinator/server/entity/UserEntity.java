package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_table")
public class UserEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private SystemRoleEntity role;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "is_locked", nullable = false)
  private Boolean isLocked;

  public UserEntity() {
  }

  public UserEntity(UserEntity userEntity) {
    this.id = userEntity.getId();
    this.fullName = userEntity.getFullName();
    this.username = userEntity.getUsername();
    this.email = userEntity.getEmail();
    this.password = userEntity.getPassword();
    this.role = userEntity.getRole();
    this.isActive = userEntity.getIsActive();
    this.isLocked = userEntity.getIsLocked();
  }

  public UserEntity(String id, String fullName, String email, String password, SystemRoleEntity role,
      Boolean isActive, Boolean isLocked) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.role = role;
    this.isActive = isActive;
    this.isLocked = isLocked;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    role.getPermissionPerResource()
        .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.toString())));
    return authorities;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public SystemRoleEntity getRole() {
    return role;
  }

  public void setRole(SystemRoleEntity role) {
    this.role = role;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Boolean getIsLocked() {
    return isLocked;
  }

  public void setIsLocked(Boolean isLocked) {
    this.isLocked = isLocked;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isActive;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isLocked;
  }

  @Override
  public boolean isEnabled() {
    return isActive;
  }

}
