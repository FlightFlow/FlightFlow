package com.flightcoordinator.server.enums;

public enum AirportType {
  INTERNATIONAL("International"),
  REGIONAL("Regional"),
  DOMESTIC("Domestic");

  public final String type;

  private AirportType(String type) {
    this.type = type;
  }
}
