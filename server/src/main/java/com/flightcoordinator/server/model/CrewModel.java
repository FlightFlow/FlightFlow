package com.flightcoordinator.server.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRoles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "crew")
public class CrewModel {
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
  private List<String> certifications;

  @NotBlank(message = "Total flight hours cannot be blank")
  private int totalFlightHours = 0;

  @NotBlank(message = "Certification cannot be blank")
  private String baseAirport;

  @NotBlank(message = "Availability cannot be blank")
  private CrewAvailability availability = CrewAvailability.AVAILABLE;

  public CrewModel(String id, String fullName, Float phoneNumber, CrewRoles role, List<String> certifications,
      int totalFlightHours, String baseAirport, CrewAvailability availability) {
    this.id = id;
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.certifications = certifications;
    this.totalFlightHours = totalFlightHours;
    this.baseAirport = baseAirport;
    this.availability = availability;
  }

  // Getter and Setters
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Float getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(Float phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public CrewRoles getRole() {
    return this.role;
  }

  public void setRole(CrewRoles role) {
    this.role = role;
  }

  public List<String> getCertifications() {
    return this.certifications;
  }

  public void setCertifications(List<String> certifications) {
    this.certifications = certifications;
  }

  public int getTotalFlightHours() {
    return this.totalFlightHours;
  }

  public void setTotalFlightHours(int totalFlightHours) {
    this.totalFlightHours = totalFlightHours;
  }

  public String getBaseAirport() {
    return this.baseAirport;
  }

  public void setBaseAirport(String baseAirport) {
    this.baseAirport = baseAirport;
  }

  public CrewAvailability getAvailability() {
    return this.availability;
  }

  public void setAvailability(CrewAvailability availability) {
    this.availability = availability;
  }

  public Boolean isPhoneNumberValid() {
    return this.phoneNumber.toString().length() == 11;
  }
}
