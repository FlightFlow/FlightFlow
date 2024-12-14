package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.List;

import com.flightcoordinator.server.enums.AirportType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "airport_table")
public class AirportEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "name", nullable = false)
  @NotBlank(message = "Name cannot be blank")
  private String name;

  @Column(name = "iata_code", nullable = false, unique = true)
  @NotBlank(message = "IATA Code cannot be blank")
  private String iataCode;

  @Column(name = "icao_code", nullable = false, unique = true)
  @NotBlank(message = "ICAO Code cannot be blank")
  private String icaoCode;

  @Column(name = "country_code", nullable = false, unique = true)
  @NotBlank(message = "Country code cannot be blank")
  private String countryCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private AirportType type;

  @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @JoinTable(name = "airport_runways", joinColumns = @JoinColumn(name = "airport_id"), inverseJoinColumns = @JoinColumn(name = "runway_id"))
  private List<RunwayEntity> runways = new ArrayList<>();

  public AirportEntity() {
  }

  public AirportEntity(String id, @NotBlank(message = "Name cannot be blank") String name,
      @NotBlank(message = "IATA Code cannot be blank") String iataCode,
      @NotBlank(message = "ICAO Code cannot be blank") String icaoCode,
      @NotBlank(message = "Country code cannot be blank") String countryCode, AirportType type,
      List<RunwayEntity> runways) {
    this.id = id;
    this.name = name;
    this.iataCode = iataCode;
    this.icaoCode = icaoCode;
    this.countryCode = countryCode;
    this.type = type;
    this.runways = runways;
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

  public String getIataCode() {
    return iataCode;
  }

  public void setIataCode(String iataCode) {
    this.iataCode = iataCode;
  }

  public String getIcaoCode() {
    return icaoCode;
  }

  public void setIcaoCode(String icaoCode) {
    this.icaoCode = icaoCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public AirportType getType() {
    return type;
  }

  public void setType(AirportType type) {
    this.type = type;
  }

  public List<RunwayEntity> getRunways() {
    return runways;
  }

  public void setRunways(List<RunwayEntity> runways) {
    this.runways = runways;
  }
}
