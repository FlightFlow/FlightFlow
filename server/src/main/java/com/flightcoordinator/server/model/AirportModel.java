package com.flightcoordinator.server.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.AirportTypes;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "airport")
public class AirportModel {
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
  private List<String> runways = new ArrayList<>();

  public AirportModel(String id, String name, String iataCode, String icaoCoda, String countryCode, AirportTypes type,
      List<String> runways) {
    this.id = id;
    this.name = name;
    this.iataCode = iataCode;
    this.icaoCode = icaoCoda;
    this.countryCode = countryCode;
    this.type = type;
    this.runways = runways;
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

  public String getIataCode() {
    return this.iataCode;
  }

  public void setIataCode(String iataCode) {
    this.iataCode = iataCode;
  }

  public String getIcaoCode() {
    return this.icaoCode;
  }

  public void setIcaoCode(String icaoCode) {
    this.icaoCode = icaoCode;
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public AirportTypes getType() {
    return this.type;
  }

  public void setType(AirportTypes type) {
    this.type = type;
  }

  public List<String> getRunways() {
    return this.runways;
  }

  public void setRunways(List<String> runways) {
    this.runways = runways;
  }
}
