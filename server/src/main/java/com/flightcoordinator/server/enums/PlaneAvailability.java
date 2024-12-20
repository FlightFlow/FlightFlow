package com.flightcoordinator.server.enums;

public enum PlaneAvailability {
  AVAILABLE("Available"),
  UNDER_MAINTENANCE("Under-Maintenance"),
  IN_USE("In-Use"),
  RETIRED("Retired");

  public final String availability;

  private PlaneAvailability(String availability) {
    this.availability = availability;
  }
}
