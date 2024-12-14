package com.flightcoordinator.server.enums;

// TODO turn into an endpoint

public enum AirportType {
  INTERNATIONAL("International"),
  REGIONAL("Regional"),
  DOMESTIC("Domestic");

  public final String type;

  private AirportType(String type) {
    this.type = type;
  }
}
