package com.flightcoordinator.server.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "route_table")
public class RouteEntity {
  @Id
  private String id;

  @NotBlank(message = "Origin airport id cannot be blank")
  @ManyToOne
  @JoinColumn(name = "origin_airport_id", nullable = false)
  private AirportEntity originAirport;

  @NotBlank(message = "Destination airport id cannot be blank")
  @ManyToOne
  @JoinColumn(name = "destination_airport_id", nullable = false)
  private AirportEntity destinationAirport;

  @NotBlank(message = "Distance cannot be blank")
  @Min(value = 1, message = "Distance should be greater than '1'")
  @Column(name = "distance", nullable = false)
  private Float distance;

  @NotBlank(message = "Estimated time cannot be blank")
  @Column(name = "estimated_time", nullable = false)
  private Float estimatedTime;

  public RouteEntity() {
  }

  public RouteEntity(String id, @NotBlank(message = "Origin airport id cannot be blank") AirportEntity originAirport,
      @NotBlank(message = "Destination airport id cannot be blank") AirportEntity destinationAirport,
      @NotBlank(message = "Distance cannot be blank") @Min(value = 1, message = "Distance should be greater than '1'") Float distance,
      @NotBlank(message = "Estimated time cannot be blank") Float estimatedTime) {
    this.id = id;
    this.originAirport = originAirport;
    this.destinationAirport = destinationAirport;
    this.distance = distance;
    this.estimatedTime = estimatedTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AirportEntity getOriginAirport() {
    return originAirport;
  }

  public void setOriginAirport(AirportEntity originAirport) {
    this.originAirport = originAirport;
  }

  public AirportEntity getDestinationAirport() {
    return destinationAirport;
  }

  public void setDestinationAirport(AirportEntity destinationAirport) {
    this.destinationAirport = destinationAirport;
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
