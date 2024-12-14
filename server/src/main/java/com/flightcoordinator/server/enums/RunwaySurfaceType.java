package com.flightcoordinator.server.enums;

// TODO turn into an endpoint

public enum RunwaySurfaceType {
  ASPHALT("Asphalt"),
  CONCRETE("Concrete"),
  GRASS("Grass"),
  GRAVEL("Gravel"),
  DIRT("Dirt"),
  COMP_MATERIAL("Composite Materials");

  public final String type;

  private RunwaySurfaceType(String type) {
    this.type = type;
  }
}
