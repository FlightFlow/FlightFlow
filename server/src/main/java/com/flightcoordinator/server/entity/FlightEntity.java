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

@Entity
@Table(name = "flight_table")
public class FlightEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "passenger_count", nullable = false)
  private Integer passengerCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_route_id", nullable = false)
  private RouteEntity flightRoute;

  public FlightEntity() {
  }

  public FlightEntity(String id, Integer passengerCount, RouteEntity flightRoute) {
    this.id = id;
    this.passengerCount = passengerCount;
    this.flightRoute = flightRoute;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getPassengerCount() {
    return passengerCount;
  }

  public void setPassengerCount(Integer passengerCount) {
    this.passengerCount = passengerCount;
  }

  public RouteEntity getFlightRoute() {
    return flightRoute;
  }

  public void setFlightRoute(RouteEntity flightRoute) {
    this.flightRoute = flightRoute;
  }
}
