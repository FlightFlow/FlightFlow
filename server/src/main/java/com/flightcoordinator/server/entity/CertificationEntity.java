package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flightcoordinator.server.enums.CertificationIssuer;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewRole;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "certification_table")
public class CertificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "Name cannot be blank")
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank(message = "Issuer cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "issuer", nullable = false)
  private CertificationIssuer issuer;

  @NotBlank(message = "Issuing country cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "issusing_country", nullable = false)
  private CertificationIssuingCountry issuingCountry;

  @NotBlank(message = "Expiration date cannot be blank")
  @Column(name = "expiration_date", nullable = false)
  private Date expirationDate;

  @NotBlank(message = "Validity period cannot be blank")
  @Min(value = 1, message = "Validity period should be greater than '1'")
  @Column(name = "validity_period", nullable = false)
  private int validityPeriod;

  @ElementCollection(targetClass = CrewRole.class)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "assignale_roles_list", joinColumns = @JoinColumn(name = "certification_id"))
  @Column(name = "assignable_roles", nullable = false)
  private List<CrewRole> assignableRoles = new ArrayList<>();

  @NotBlank(message = "Description cannot be blank")
  @Column(name = "description", nullable = false)
  private String description;

  public CertificationEntity() {
  }

  public CertificationEntity(String id, @NotBlank(message = "Name cannot be blank") String name,
      @NotBlank(message = "Issuer cannot be blank") CertificationIssuer issuer,
      @NotBlank(message = "Issuing country cannot be blank") CertificationIssuingCountry issuingCountry,
      @NotBlank(message = "Expiration date cannot be blank") Date expirationDate,
      @NotBlank(message = "Validity period cannot be blank") @Min(value = 1, message = "Validity period should be greater than '1'") int validityPeriod,
      List<CrewRole> assignableRoles, @NotBlank(message = "Description cannot be blank") String description) {
    this.id = id;
    this.name = name;
    this.issuer = issuer;
    this.issuingCountry = issuingCountry;
    this.expirationDate = expirationDate;
    this.validityPeriod = validityPeriod;
    this.assignableRoles = assignableRoles;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CertificationIssuer getIssuer() {
    return issuer;
  }

  public void setIssuer(CertificationIssuer issuer) {
    this.issuer = issuer;
  }

  public CertificationIssuingCountry getIssuingCountry() {
    return issuingCountry;
  }

  public void setIssuingCountry(CertificationIssuingCountry issuingCountry) {
    this.issuingCountry = issuingCountry;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getValidityPeriod() {
    return validityPeriod;
  }

  public void setValidityPeriod(int validityPeriod) {
    this.validityPeriod = validityPeriod;
  }

  public List<CrewRole> getAssignableRoles() {
    return assignableRoles;
  }

  public void setAssignableRoles(List<CrewRole> assignableRoles) {
    this.assignableRoles = assignableRoles;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
