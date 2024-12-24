package com.flightcoordinator.server.dto;

public class FlightDTO {
  private String id;
  private Integer passengerCount;
  private String flightRouteId;

  public FlightDTO() {
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

  public String getFlightRouteId() {
    return flightRouteId;
  }

  public void setFlightRouteId(String flightRouteId) {
    this.flightRouteId = flightRouteId;
  }
}
