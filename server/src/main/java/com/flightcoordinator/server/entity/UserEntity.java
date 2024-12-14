package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_table")
public class UserEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "Full name cannot be blank")
  @Column(name = "full_name", nullable = false)
  private String fullName;

  @NotBlank(message = "E-Mail cannot be blank")
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NotBlank(message = "Password cannot be blank")
  @Column(name = "password", nullable = false)
  private String password;

  @NotBlank(message = "Role ID cannot be blank")
  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private SystemRoleEntity role;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "is_locked", nullable = false)
  private Boolean isLocked;

  public UserEntity() {
  }

  public UserEntity(String id, @NotBlank(message = "Full name cannot be blank") String fullName,
      @NotBlank(message = "E-Mail cannot be blank") String email,
      @NotBlank(message = "Password cannot be blank") String password,
      @NotBlank(message = "Role ID cannot be blank") SystemRoleEntity role, Boolean isActive, Boolean isLocked) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.role = role;
    this.isActive = isActive;
    this.isLocked = isLocked;
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
  public String getUsername() {
    return email; // for authentication only
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    role.getAllPermissions().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !isActive;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return !isActive;
  }
}
