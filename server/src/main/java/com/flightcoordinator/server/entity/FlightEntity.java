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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "flight_table")
public class FlightEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull(message = "Passenger count cannot be null")
  @Column(name = "passenger_count", nullable = false)
  private Integer passengerCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_route_id", nullable = false)
  private RouteEntity flightRoute;

  public FlightEntity() {
  }

  public FlightEntity(String id, @NotNull(message = "Passenger count cannot be null") Integer passengerCount,
      RouteEntity flightRoute) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((passengerCount == null) ? 0 : passengerCount.hashCode());
    result = prime * result + ((flightRoute == null) ? 0 : flightRoute.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FlightEntity other = (FlightEntity) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (passengerCount == null) {
      if (other.passengerCount != null)
        return false;
    } else if (!passengerCount.equals(other.passengerCount))
      return false;
    if (flightRoute == null) {
      if (other.flightRoute != null)
        return false;
    } else if (!flightRoute.equals(other.flightRoute))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "FlightEntity [id=" + id + ", passengerCount=" + passengerCount + ", flightRoute=" + flightRoute
        + ", getId()=" + getId() + ", getPassengerCount()=" + getPassengerCount() + ", getFlightRoute()="
        + getFlightRoute() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
        + super.toString() + "]";
  }

}
