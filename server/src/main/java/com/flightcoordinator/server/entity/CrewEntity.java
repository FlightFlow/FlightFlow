package com.flightcoordinator.server.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "crew_table")
public class CrewEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "Full name cannot be blank")
  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Email(message = "E-Mail is invalid")
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @NotBlank(message = "Phone number cannot be blank")
  @Column(name = "phone_number", nullable = false)
  private Float phoneNumber;

  @NotBlank(message = "Role cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private CrewRole role;

  @ManyToMany
  @JoinTable(name = "certifications", joinColumns = @JoinColumn(name = "crew_id"), inverseJoinColumns = @JoinColumn(name = "certification_id"))
  private List<CertificationEntity> certifications;

  @NotBlank(message = "Total flight hours cannot be blank")
  @Column(name = "total_flight_hours", nullable = false)
  private int totalFlightHours = 0;

  @NotBlank(message = "Certification cannot be blank")
  @OneToMany
  @JoinColumn(name = "base_airport", nullable = false)
  private AirportEntity baseAirport;

  @NotBlank(message = "Availability cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "availability", nullable = false)
  private CrewAvailability availability = CrewAvailability.AVAILABLE;

  public CrewEntity() {
  }

  public CrewEntity(String id, @NotBlank(message = "Full name cannot be blank") String fullName,
      @Email(message = "E-Mail is invalid") String email,
      @NotBlank(message = "Phone number cannot be blank") Float phoneNumber,
      @NotBlank(message = "Role cannot be blank") CrewRole role, List<CertificationEntity> certifications,
      @NotBlank(message = "Total flight hours cannot be blank") int totalFlightHours,
      @NotBlank(message = "Certification cannot be blank") AirportEntity baseAirport,
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

  public CrewRole getRole() {
    return role;
  }

  public void setRole(CrewRole role) {
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

  public AirportEntity getBaseAirport() {
    return baseAirport;
  }

  public void setBaseAirport(AirportEntity baseAirport) {
    this.baseAirport = baseAirport;
  }

  public CrewAvailability getAvailability() {
    return availability;
  }

  public void setAvailability(CrewAvailability availability) {
    this.availability = availability;
  }
}
