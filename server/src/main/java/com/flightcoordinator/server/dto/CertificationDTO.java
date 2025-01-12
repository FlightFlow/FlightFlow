package com.flightcoordinator.server.dto;

import java.util.Date;

import com.flightcoordinator.server.enums.CertificationIssuer;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewRole;

public class CertificationDTO {
  private String id;
  private String name;
  private Integer certificationNumber;
  private CertificationIssuer issuer;
  private CertificationIssuingCountry issuingCountry;
  private Date expirationDate;
  private Integer validityPeriod;
  private CrewRole assignableRole;
  private String description;
  private String assignedCrewMember;

  public CertificationDTO() {
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

  public String getAssignedCrewMember() {
    return assignedCrewMember;
  }

  public void setAssignedCrewMember(String assignedCrewMember) {
    this.assignedCrewMember = assignedCrewMember;
  }
}
