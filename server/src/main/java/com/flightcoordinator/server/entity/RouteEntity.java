package com.flightcoordinator.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "route")
public class RouteEntity {
  @Id
  private String id;

  @Field("origin_airport_id")
  @NotBlank(message = "Origin airport id cannot be blank")
  private String originAirportId;

  @Field("destination_airport_id")
  @NotBlank(message = "Destination airport id cannot be blank")
  private String destinationAirportId;

  @Field("distance")
  @NotBlank(message = "Distance cannot be blank")
  @Min(value = 1, message = "Distance should be greater than '1'")
  private Float distance;

  @Field("estimated_time")
  @NotBlank(message = "Estimated time cannot be blank")
  private Float estimatedTime;

  public RouteEntity() {
  }

  public RouteEntity(String id, @NotBlank(message = "Origin airport id cannot be blank") String originAirportId,
      @NotBlank(message = "Destination airport id cannot be blank") String destinationAirportId,
      @NotBlank(message = "Distance cannot be blank") @Min(value = 1, message = "Distance should be greater than '1'") Float distance,
      @NotBlank(message = "Estimated time cannot be blank") Float estimatedTime) {
    this.id = id;
    this.originAirportId = originAirportId;
    this.destinationAirportId = destinationAirportId;
    this.distance = distance;
    this.estimatedTime = estimatedTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOriginAirportId() {
    return originAirportId;
  }

  public void setOriginAirportId(String originAirportId) {
    this.originAirportId = originAirportId;
  }

  public String getDestinationAirportId() {
    return destinationAirportId;
  }

  public void setDestinationAirportId(String destinationAirportId) {
    this.destinationAirportId = destinationAirportId;
  }

  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public Float getEstimatedTime() {
    return estimatedTime;
  }

  public void setEstimatedTime(Float estimatedTime) {
    this.estimatedTime = estimatedTime;
  }

}
