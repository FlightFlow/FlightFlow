package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.flightcoordinator.server.enums.CertificationIssuers;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewRoles;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "certifications")
public class CertificationEntity {
  @Id
  private String id;

  @Field("name")
  @NotBlank(message = "Name cannot be blank")
  private String name;

  @Field("issuer")
  @NotBlank(message = "Issuer cannot be blank")
  private CertificationIssuers issuer;

  @Field("issusing_country")
  @NotBlank(message = "Issuing country cannot be blank")
  private CertificationIssuingCountry issuingCountry;

  @Field("expiration_date")
  @NotBlank(message = "Expiration date cannot be blank")
  private Date expirationDate;

  @Field("validity_period")
  @NotBlank(message = "Validity period cannot be blank")
  @Min(value = 1, message = "Validity period should be greater than '1'")
  private int validityPeriod;

  @Field("assignable_roles")
  private List<CrewRoles> assignableRoles = new ArrayList<>();

  @Field("description")
  @NotBlank(message = "Description cannot be blank")
  private String description;

  public CertificationEntity() {
  }

  public CertificationEntity(String id, @NotBlank(message = "Name cannot be blank") String name,
      @NotBlank(message = "Issuer cannot be blank") CertificationIssuers issuer,
      @NotBlank(message = "Issuing country cannot be blank") CertificationIssuingCountry issuingCountry,
      @NotBlank(message = "Expiration date cannot be blank") Date expirationDate,
      @NotBlank(message = "Validity period cannot be blank") @Min(value = 1, message = "Validity period should be greater than '1'") int validityPeriod,
      List<CrewRoles> assignableRoles, @NotBlank(message = "Description cannot be blank") String description) {
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

  public CertificationIssuers getIssuer() {
    return issuer;
  }

  public void setIssuer(CertificationIssuers issuer) {
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

  public List<CrewRoles> getAssignableRoles() {
    return assignableRoles;
  }

  public void setAssignableRoles(List<CrewRoles> assignableRoles) {
    this.assignableRoles = assignableRoles;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
