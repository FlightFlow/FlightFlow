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

@Entity
@Table(name = "route_table")
public class RouteEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_airport_id", nullable = false)
  private AirportEntity originAirport;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_airport_id", nullable = false)
  private AirportEntity destinationAirport;

  @Min(value = 1, message = "Distance should be greater than '1'")
  @Column(name = "distance", nullable = false)
  private Float distance;

  @Column(name = "estimated_time", nullable = false)
  private Float estimatedTime;

  public RouteEntity() {
  }

  public RouteEntity(String id, AirportEntity originAirport, AirportEntity destinationAirport,
      @Min(value = 1, message = "Distance should be greater than '1'") Float distance, Float estimatedTime) {
    this.id = id;
    this.originAirport = originAirport;
    this.destinationAirport = destinationAirport;
    this.distance = distance;
    this.estimatedTime = estimatedTime;
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
}
