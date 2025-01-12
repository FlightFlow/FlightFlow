package com.flightcoordinator.server.dto;

import java.util.Date;
import java.util.List;

import com.flightcoordinator.server.enums.CertificationIssuer;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewRole;

public class CertificationDTO {
  private String id;
  private String name;
  private CertificationIssuer issuer;
  private CertificationIssuingCountry issuingCountry;
  private Date expirationDate;
  private Integer validityPeriod;
  private List<CrewRole> assignableRole;
  private String description;
  private List<String> assignedCrewMembers;

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

  public List<CrewRole> getAssignableRole() {
    return assignableRole;
  }

  public void setAssignableRole( List<CrewRole> assignableRole) {
    this.assignableRole = assignableRole;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getAssignedCrewMembers() {
    return assignedCrewMembers;
  }

  public void setAssignedCrewMembers(List<String> assignedCrewMembers) {
    this.assignedCrewMembers = assignedCrewMembers;
  }
}
