package com.flightcoordinator.server.dto;

public class RouteDTO {
  private String id;
  private String originAirportId;
  private String destinationAirportId;
  private Float distance;
  private Float estimatedTime;

  public RouteDTO() {
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
