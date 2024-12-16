package com.flightcoordinator.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "route_table")
public class RouteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull(message = "Origin airport id cannot be null")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_airport_id", nullable = false)
  private AirportEntity originAirport;

  @NotNull(message = "Destination airport id cannot be null")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_airport_id", nullable = false)
  private AirportEntity destinationAirport; // This field corresponds to the 'destination' in AirportEntity

  @NotNull(message = "Distance cannot be null")
  @Min(value = 1, message = "Distance should be greater than '1'")
  @Column(name = "distance", nullable = false)
  private Float distance;

  @NotNull(message = "Estimated time cannot be null")
  @Column(name = "estimated_time", nullable = false)
  private Float estimatedTime;

  public RouteEntity(String id, @NotNull(message = "Origin airport id cannot be null") AirportEntity originAirport,
      @NotNull(message = "Destination airport id cannot be null") AirportEntity destinationAirport,
      @NotNull(message = "Distance cannot be null") @Min(value = 1, message = "Distance should be greater than '1'") Float distance,
      @NotNull(message = "Estimated time cannot be null") Float estimatedTime) {
    this.id = id;
    this.originAirport = originAirport;
    this.destinationAirport = destinationAirport;
    this.distance = distance;
    this.estimatedTime = estimatedTime;
  }

  public RouteEntity() {
  }

  public AirportEntity getDestinationAirport() {
    return destinationAirport;
  }

  public void setDestinationAirport(AirportEntity destinationAirport) {
    this.destinationAirport = destinationAirport;
  }

  public AirportEntity getOriginAirport() {
    return originAirport;
  }

  public void setOriginAirport(AirportEntity originAirport) {
    this.originAirport = originAirport;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "RouteEntity [id=" + id + ", originAirport=" + originAirport + ", destinationAirport=" + destinationAirport
        + ", distance=" + distance + ", estimatedTime=" + estimatedTime + ", getId()=" + getId()
        + ", getOriginAirport()=" + getOriginAirport() + ", getDestinationAirport()=" + getDestinationAirport()
        + ", getDistance()=" + getDistance() + ", getEstimatedTime()=" + getEstimatedTime() + ", hashCode()="
        + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
  }
}
