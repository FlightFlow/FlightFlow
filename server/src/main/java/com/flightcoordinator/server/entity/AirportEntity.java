package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.List;

import com.flightcoordinator.server.enums.AirportTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "airports")
public class AirportEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @NotBlank(message = "Name cannot be blank")
  @Column(nullable = false)
  private String name;

  @NotBlank(message = "IATA Code cannot be blank")
  @Column(name = "iata_code", nullable = true, unique = true, length = 3)
  private String iataCode;

  @NotBlank(message = "ICAO Code cannot be blank")
  @Column(name = "icao_code", nullable = true, unique = true, length = 4)
  private String icaoCode;

  @NotBlank(message = "Country code cannot be blank")
  @Column(name = "country_code", nullable = true, unique = true, length = 2)
  private String countryCode;

  @NotBlank(message = "Type cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private AirportTypes type;

  @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RunwayEntity> runways = new ArrayList<>();

  public AirportEntity() {
  }

  public AirportEntity(String id, @NotBlank(message = "Name cannot be blank") String name,
      @NotBlank(message = "IATA Code cannot be blank") String iataCode,
      @NotBlank(message = "ICAO Code cannot be blank") String icaoCode,
      @NotBlank(message = "Country code cannot be blank") String countryCode,
      @NotBlank(message = "Type cannot be blank") AirportTypes type, List<RunwayEntity> runways) {
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

  public AirportTypes getType() {
    return type;
  }

  public void setType(AirportTypes type) {
    this.type = type;
  }

  public List<RunwayEntity> getRunways() {
    return runways;
  }

  public void setRunways(List<RunwayEntity> runways) {
    this.runways = runways;
  }
}
