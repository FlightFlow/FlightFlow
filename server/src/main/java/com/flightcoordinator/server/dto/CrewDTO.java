package com.flightcoordinator.server.dto;

import java.util.List;

import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRole;

public class CrewDTO {
  private String id;
  private String fullName;
  private String email;
  private Long phoneNumber;
  private CrewRole role;
  private List<String> certificationIds;
  private Integer totalFlightHours;
  private String baseAirportId;
  private CrewAvailability availability;

  public CrewDTO() {
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

  public List<String> getCertificationIds() {
    return certificationIds;
  }

  public void setCertificationIds(List<String> certificationIds) {
    this.certificationIds = certificationIds;
  }

  public Integer getTotalFlightHours() {
    return totalFlightHours;
  }

  public void setTotalFlightHours(Integer totalFlightHours) {
    this.totalFlightHours = totalFlightHours;
  }

  public String getBaseAirportId() {
    return baseAirportId;
  }

  public void setBaseAirportId(String baseAirportId) {
    this.baseAirportId = baseAirportId;
  }

  public CrewAvailability getAvailability() {
    return availability;
  }

  public void setAvailability(CrewAvailability availability) {
    this.availability = availability;
  }
}
