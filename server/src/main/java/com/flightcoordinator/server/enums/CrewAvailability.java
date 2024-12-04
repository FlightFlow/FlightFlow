package com.flightcoordinator.server.enums;

public enum CrewAvailability {
  AVAILABLE("Available"),
  ON_DUTY("On-Duty"),
  ON_LEAVE("On-Leave"),
  UNAVAILABLE("Unavailable");

  public final String availability;

  private CrewAvailability(String availability) {
    this.availability = availability;
  }
}
