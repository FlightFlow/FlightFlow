package com.flightcoordinator.server.dto;

import com.flightcoordinator.server.enums.RunwaySurfaceType;

public class RunwayDTO {
  private String id;
  private Float length;
  private Float width;
  private RunwaySurfaceType surfaceType;
  private Float maxWeightCapacity;
  private String orientation;
  private String airportId;

  public RunwayDTO() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Float getLength() {
    return length;
  }

  public void setLength(Float length) {
    this.length = length;
  }

  public Float getWidth() {
    return width;
  }

  public void setWidth(Float width) {
    this.width = width;
  }

  public RunwaySurfaceType getSurfaceType() {
    return surfaceType;
  }

  public void setSurfaceType(RunwaySurfaceType surfaceType) {
    this.surfaceType = surfaceType;
  }

  public Float getMaxWeightCapacity() {
    return maxWeightCapacity;
  }

  public void setMaxWeightCapacity(Float maxWeightCapacity) {
    this.maxWeightCapacity = maxWeightCapacity;
  }

  public String getOrientation() {
    return orientation;
  }

  public void setOrientation(String orientation) {
    this.orientation = orientation;
  }

  public String getAirportId() {
    return airportId;
  }

  public void setAirportId(String airportId) {
    this.airportId = airportId;
  }
}
