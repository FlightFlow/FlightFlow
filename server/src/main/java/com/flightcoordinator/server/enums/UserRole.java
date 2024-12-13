package com.flightcoordinator.server.enums;

public enum UserRole {
  ADMIN("Admin"), // manages -> all controllers
  OPR_DATA_MANAGER("Operations Data Manager"), // manages -> route, plane, vehicle
  CREW_MANAGER("Crew Manager"), // manages -> crew, certification
  INFRA_MANAGER("Infrastructure Manager"); // manages -> airport, runway, vehicle

  private final String title;

  private UserRole(String title) {
    this.title = title;
  }

  public String getTitle() {
    return this.title;
  }
}
