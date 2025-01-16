package com.flightcoordinator.server.entity;

import java.util.List;

import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "crew_table")
public class CrewEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Email(message = "E-Mail is invalid")
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "phone_number", nullable = false)
  private Long phoneNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private CrewRole role;

  @OneToMany(mappedBy = "assignedCrewMember", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<CertificationEntity> certifications;

  @Min(value = 0, message = "Total flight hours cannot be negative")
  @Column(name = "total_flight_hours", nullable = false)
  private Integer totalFlightHours = 0;

  @ManyToOne
  @JoinColumn(name = "base_airport", nullable = false)
  private AirportEntity baseAirport;

  @Enumerated(EnumType.STRING)
  @Column(name = "availability", nullable = false)
  private CrewAvailability availability;

  public CrewEntity() {
  }

  public CrewEntity(String id, String fullName, @Email(message = "E-Mail is invalid") String email, Long phoneNumber,
      CrewRole role, List<CertificationEntity> certifications,
      @Min(value = 0, message = "Total flight hours cannot be negative") Integer totalFlightHours,
      AirportEntity baseAirport, CrewAvailability availability) {
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

  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
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

  public Integer getTotalFlightHours() {
    return totalFlightHours;
  }

  public void setTotalFlightHours(Integer totalFlightHours) {
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
