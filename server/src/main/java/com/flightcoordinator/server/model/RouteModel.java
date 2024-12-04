package com.flightcoordinator.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "route")
public class RouteModel {
  @Id
  private String id;

  @NotBlank(message = "Origin airport id cannot be blank")
  private String originAirportId;

  @NotBlank(message = "Destination airport id cannot be blank")
  private String destinationAirportId;

  @NotBlank(message = "Distance cannot be blank")
  @Min(value = 1, message = "Distance should be greater than '1'")
  private Float distance;

  @NotBlank(message = "Estimated time cannot be blank")
  private Float estimatedTime;

  public RouteModel(String id, String originAirportId, String destinationAirportId, Float distance,
      Float estimatedTime) {
    this.id = id;
    this.originAirportId = originAirportId;
    this.destinationAirportId = destinationAirportId;
    this.distance = distance;
    this.estimatedTime = estimatedTime;
  }

  // Getter and Setters
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOriginAirportId() {
    return this.originAirportId;
  }

  public void setOriginAirportId(String originAirportId) {
    this.originAirportId = originAirportId;
  }

  public String getDestinationAirportId() {
    return this.destinationAirportId;
  }

  public void setDestinationAirportId(String destinationAirportId) {
    this.destinationAirportId = destinationAirportId;
  }

  public Float getDistance() {
    return this.distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public Float getEstimatedTime() {
    return this.estimatedTime;
  }

  public void setEstimatedTime(Float estimatedTime) {
    this.estimatedTime = estimatedTime;
  }
}
