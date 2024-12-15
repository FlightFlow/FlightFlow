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
    private int validityPeriod;

    @ElementCollection(targetClass = CrewRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "assignable_roles_list", joinColumns = @JoinColumn(name = "certification_id"))
    @Column(name = "assignable_roles", nullable = false)
    private List<CrewRole> assignableRoles = new ArrayList<>();

    @NotBlank(message = "Description cannot be blank")
    @Column(name = "description", nullable = false)
    private String description;

    public CertificationEntity() {
    }

    public CertificationEntity(String id, @NotBlank(message = "Name cannot be blank") String name,
        CertificationIssuer issuer, CertificationIssuingCountry issuingCountry, Date expirationDate,
        @Min(value = 1, message = "Validity period should be greater than '1'") int validityPeriod,
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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((issuer == null) ? 0 : issuer.hashCode());
      result = prime * result + ((issuingCountry == null) ? 0 : issuingCountry.hashCode());
      result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
      result = prime * result + validityPeriod;
      result = prime * result + ((assignableRoles == null) ? 0 : assignableRoles.hashCode());
      result = prime * result + ((description == null) ? 0 : description.hashCode());
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
      CertificationEntity other = (CertificationEntity) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      if (name == null) {
        if (other.name != null)
          return false;
      } else if (!name.equals(other.name))
        return false;
      if (issuer != other.issuer)
        return false;
      if (issuingCountry != other.issuingCountry)
        return false;
      if (expirationDate == null) {
        if (other.expirationDate != null)
          return false;
      } else if (!expirationDate.equals(other.expirationDate))
        return false;
      if (validityPeriod != other.validityPeriod)
        return false;
      if (assignableRoles == null) {
        if (other.assignableRoles != null)
          return false;
      } else if (!assignableRoles.equals(other.assignableRoles))
        return false;
      if (description == null) {
        if (other.description != null)
          return false;
      } else if (!description.equals(other.description))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "CertificationEntity [id=" + id + ", name=" + name + ", issuer=" + issuer + ", issuingCountry="
          + issuingCountry + ", expirationDate=" + expirationDate + ", validityPeriod=" + validityPeriod
          + ", assignableRoles=" + assignableRoles + ", description=" + description + ", getId()=" + getId()
          + ", getName()=" + getName() + ", getIssuer()=" + getIssuer() + ", getIssuingCountry()=" + getIssuingCountry()
          + ", getExpirationDate()=" + getExpirationDate() + ", getValidityPeriod()=" + getValidityPeriod()
          + ", getAssignableRoles()=" + getAssignableRoles() + ", getDescription()=" + getDescription()
          + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
    }

    
}
