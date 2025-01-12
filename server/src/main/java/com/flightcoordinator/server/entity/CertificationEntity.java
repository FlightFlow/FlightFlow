package com.flightcoordinator.server.entity;

import java.util.Date;

import com.flightcoordinator.server.enums.CertificationIssuer;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "certification_table")
public class CertificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "certification_number", nullable = false)
  private Integer certificationNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "issuer", nullable = false)
  private CertificationIssuer issuer;

  @Enumerated(EnumType.STRING)
  @Column(name = "issuing_country", nullable = false)
  private CertificationIssuingCountry issuingCountry;

  @Column(name = "expiration_date", nullable = false)
  private Date expirationDate;

  @Min(value = 1, message = "Validity period should be greater than '1'")
  @Column(name = "validity_period", nullable = false)
  private Integer validityPeriod;

  @Enumerated(EnumType.STRING)
  @Column(name = "assignable_roles", nullable = false)
  private CrewRole assignableRole;

  @Column(name = "description", nullable = false)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  private CrewEntity assignedCrewMember;

  public CertificationEntity() {
  }

  public CertificationEntity(String id, String name, Integer certificationNumber, CertificationIssuer issuer,
      CertificationIssuingCountry issuingCountry, Date expirationDate,
      @Min(value = 1, message = "Validity period should be greater than '1'") Integer validityPeriod,
      CrewRole assignableRole, String description, CrewEntity assignedCrewMember) {
    this.id = id;
    this.name = name;
    this.certificationNumber = certificationNumber;
    this.issuer = issuer;
    this.issuingCountry = issuingCountry;
    this.expirationDate = expirationDate;
    this.validityPeriod = validityPeriod;
    this.assignableRole = assignableRole;
    this.description = description;
    this.assignedCrewMember = assignedCrewMember;
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

  public Integer getCertificationNumber() {
    return certificationNumber;
  }

  public void setCertificationNumber(Integer certificationNumber) {
    this.certificationNumber = certificationNumber;
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

  public Integer getValidityPeriod() {
    return validityPeriod;
  }

  public void setValidityPeriod(Integer validityPeriod) {
    this.validityPeriod = validityPeriod;
  }

  public CrewRole getAssignableRole() {
    return assignableRole;
  }

  public void setAssignableRole(CrewRole assignableRole) {
    this.assignableRole = assignableRole;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CrewEntity getAssignedCrewMember() {
    return assignedCrewMember;
  }

  public void setAssignedCrewMember(CrewEntity assignedCrewMember) {
    this.assignedCrewMember = assignedCrewMember;
  }

}
