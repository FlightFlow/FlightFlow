package com.flightcoordinator.server.enums;

public enum GroundVehicleAvailability {
  AVAILABLE("Available"),
  IN_USE("In-Use"),
  UNDER_MAINTENANCE("Under-Maintenance"),
  OUT_OF_SERVICE("Out-Of-Service");

  public final String availability;

  private GroundVehicleAvailability(String availability) {
    this.availability = availability;
  }
}
