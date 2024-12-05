package com.flightcoordinator.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.CertificationIssuers;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewRoles;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "certification")
public class CertificationModel {
  @Id
  private String id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "Issuer cannot be blank")
  private CertificationIssuers issuer;

  @NotBlank(message = "Issuing country cannot be blank")
  private CertificationIssuingCountry issuingCountry;

  @NotBlank(message = "Expiration date cannot be blank")
  private Date expirationDate;

  @NotBlank(message = "Validity period cannot be blank")
  @Min(value = 1, message = "Validity period should be greater than '1'")
  private int validityPeriod;
  private List<CrewRoles> assignableRoles = new ArrayList<>();

  @NotBlank(message = "Description cannot be blank")
  private String description;

  public CertificationModel(String id, String name, CertificationIssuers issuer,
      CertificationIssuingCountry issuingCountry, Date expirationDate, int validityPeriod,
      List<CrewRoles> assignableRoles, String description) {
    this.id = id;
    this.name = name;
    this.issuer = issuer;
    this.issuingCountry = issuingCountry;
    this.expirationDate = expirationDate;
    this.validityPeriod = validityPeriod;
    this.assignableRoles = assignableRoles;
    this.description = description;
  }

  // Getter and Setters
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CertificationIssuers getIssuer() {
    return this.issuer;
  }

  public void setIssuer(CertificationIssuers issuer) {
    this.issuer = issuer;
  }

  public CertificationIssuingCountry getIssuingCountry() {
    return this.issuingCountry;
  }

  public void setIssuingCountry(CertificationIssuingCountry issuingCountry) {
    this.issuingCountry = issuingCountry;
  }

  public Date getExpirationDate() {
    return this.expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getValidityPeriod() {
    return this.validityPeriod;
  }

  public void setValidityPeriod(int validityPeriod) {
    this.validityPeriod = validityPeriod;
  }

  public List<CrewRoles> getAssignableRoles() {
    return this.assignableRoles;
  }

  public void setAssignableRoles(List<CrewRoles> assignableRoles) {
    this.assignableRoles = assignableRoles;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
