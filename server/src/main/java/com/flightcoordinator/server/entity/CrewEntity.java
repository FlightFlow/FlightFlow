package com.flightcoordinator.server.entity;

import java.util.List;

import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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
  private Integer phoneNumber;

  @NotBlank(message = "Role cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private CrewRole role;

  @ManyToMany
  @JoinTable(name = "certifications", joinColumns = @JoinColumn(name = "crew_id"), inverseJoinColumns = @JoinColumn(name = "certification_id"))
  private List<CertificationEntity> certifications;

  @Min(value = 0, message = "Total flight hours cannot be negative")
  @Column(name = "total_flight_hours", nullable = false)
  private int totalFlightHours = 0;

  @ManyToOne
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
      @NotBlank(message = "Phone number cannot be blank") Integer phoneNumber,
      @NotBlank(message = "Role cannot be blank") CrewRole role, List<CertificationEntity> certifications,
      @Min(value = 0, message = "Total flight hours cannot be negative") int totalFlightHours,
      AirportEntity baseAirport, @NotBlank(message = "Availability cannot be blank") CrewAvailability availability) {
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

  public Integer getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Integer phoneNumber) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    result = prime * result + ((certifications == null) ? 0 : certifications.hashCode());
    result = prime * result + totalFlightHours;
    result = prime * result + ((baseAirport == null) ? 0 : baseAirport.hashCode());
    result = prime * result + ((availability == null) ? 0 : availability.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CrewEntity other = (CrewEntity) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (fullName == null) {
      if (other.fullName != null)
        return false;
    } else if (!fullName.equals(other.fullName))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (role != other.role)
      return false;
    if (certifications == null) {
      if (other.certifications != null)
        return false;
    } else if (!certifications.equals(other.certifications))
      return false;
    if (totalFlightHours != other.totalFlightHours)
      return false;
    if (baseAirport == null) {
      if (other.baseAirport != null)
        return false;
    } else if (!baseAirport.equals(other.baseAirport))
      return false;
    if (availability != other.availability)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CrewEntity [id=" + id + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber
        + ", role=" + role + ", certifications=" + certifications + ", totalFlightHours=" + totalFlightHours
        + ", baseAirport=" + baseAirport + ", availability=" + availability + ", getId()=" + getId()
        + ", getFullName()=" + getFullName() + ", getEmail()=" + getEmail() + ", getPhoneNumber()=" + getPhoneNumber()
        + ", getRole()=" + getRole() + ", getCertifications()=" + getCertifications() + ", getTotalFlightHours()="
        + getTotalFlightHours() + ", getBaseAirport()=" + getBaseAirport() + ", getAvailability()=" + getAvailability()
        + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
  }
  
}
  

