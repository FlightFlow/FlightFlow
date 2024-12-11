package com.flightcoordinator.server.enums;

// TODO turn into an endpoint

public enum AirportTypes {
  INTERNATIONAL("International"),
  REGIONAL("Regional"),
  DOMESTIC("Domestic");

  public final String type;

  private AirportTypes(String type) {
    this.type = type;
  }
}
