package com.flightcoordinator.server.entity;

import java.util.List;

import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRoles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "crew")
public class CrewEntity {
  @Id
  private String id;

  @NotBlank(message = "Full name cannot be blank")
  private String fullName;

  @Email(message = "E-Mail is invalid")
  private String email;

  @NotBlank(message = "Phone number cannot be blank")
  private Float phoneNumber;

  @NotBlank(message = "Role cannot be blank")
  private CrewRoles role;
  private List<CertificationEntity> certifications;

  @NotBlank(message = "Total flight hours cannot be blank")
  private int totalFlightHours = 0;

  @NotBlank(message = "Certification cannot be blank")
  private String baseAirport;

  @NotBlank(message = "Availability cannot be blank")
  private CrewAvailability availability = CrewAvailability.AVAILABLE;

  public CrewEntity() {
  }

  public CrewEntity(String id, @NotBlank(message = "Full name cannot be blank") String fullName,
      @Email(message = "E-Mail is invalid") String email,
      @NotBlank(message = "Phone number cannot be blank") Float phoneNumber,
      @NotBlank(message = "Role cannot be blank") CrewRoles role, List<CertificationEntity> certifications,
      @NotBlank(message = "Total flight hours cannot be blank") int totalFlightHours,
      @NotBlank(message = "Certification cannot be blank") String baseAirport,
      @NotBlank(message = "Availability cannot be blank") CrewAvailability availability) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.certifications = certifications;
    this.totalFlightHours = totalFlightHours;
    this.baseAirport = baseAirport;
    this.availability = availability;
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

  public Float getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Float phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public CrewRoles getRole() {
    return role;
  }

  public void setRole(CrewRoles role) {
    this.role = role;
  }

  public List<CertificationEntity> getCertifications() {
    return certifications;
  }

  public void setCertifications(List<CertificationEntity> certifications) {
    this.certifications = certifications;
  }

  public int getTotalFlightHours() {
    return totalFlightHours;
  }

  public void setTotalFlightHours(int totalFlightHours) {
    this.totalFlightHours = totalFlightHours;
  }

  public String getBaseAirport() {
    return baseAirport;
  }

  public void setBaseAirport(String baseAirport) {
    this.baseAirport = baseAirport;
  }

  public CrewAvailability getAvailability() {
    return availability;
  }

  public void setAvailability(CrewAvailability availability) {
    this.availability = availability;
  }
}
