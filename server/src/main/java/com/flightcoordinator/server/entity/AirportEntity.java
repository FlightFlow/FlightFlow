package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.AirportTypes;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "airports")
public class AirportEntity {
  @Id
  private String id;

  @NotBlank(message = "Name cannot be blank")
  private String name;

  @NotBlank(message = "IATA Code cannot be blank")
  private String iataCode;

  @NotBlank(message = "ICAO Code cannot be blank")
  private String icaoCode;

  @NotBlank(message = "Country code cannot be blank")
  private String countryCode;

  @NotBlank(message = "Type cannot be blank")
  private AirportTypes type;

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
