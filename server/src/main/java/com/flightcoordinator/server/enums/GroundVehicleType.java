package com.flightcoordinator.server.enums;

public enum GroundVehicleType {
  TUG("Tug"),
  REFUELER("Refueler"),
  LOADER("Loader"),
  CATERING("Catering"),
  DE_ICER("De-Icer"),
  PUSHBACK("Pushback"),
  BUS("Bus");

  public final String type;

  private GroundVehicleType(String type) {
    this.type = type;
  }
}
