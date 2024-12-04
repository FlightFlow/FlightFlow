package com.flightcoordinator.server.enums;

public enum AirportTypes {
  INTERNATIONAL("International"),
  REGIONAL("Regional"),
  DOMESTIC("Domestic");

  public final String type;

  private AirportTypes(String type) {
    this.type = type;
  }
}
