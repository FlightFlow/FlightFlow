package com.flightcoordinator.server.dto;

import java.util.List;

import com.flightcoordinator.server.enums.AirportType;

public class AirportDTO {
  private String id;
  private String name;
  private String iataCode;
  private String icaoCode;
  private String countryCode;
  private AirportType type;
  private List<String> runwayIds;
  private List<String> vehiclesPresentIds;
  private List<String> planesPresentIds;
  private List<String> routesOriginatingFromAirportIds;
  private List<String> routesDestinedForAirportIds;
  private List<String> crewMembersPresentIds;

  public AirportDTO() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIataCode() {
    return iataCode;
  }

  public void setIataCode(String iataCode) {
    this.iataCode = iataCode;
  }

  public String getIcaoCode() {
    return icaoCode;
  }

  public void setIcaoCode(String icaoCode) {
    this.icaoCode = icaoCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public AirportType getType() {
    return type;
  }

  public void setType(AirportType type) {
    this.type = type;
  }

  public List<String> getRunwayIds() {
    return runwayIds;
  }

  public void setRunwayIds(List<String> runwayIds) {
    this.runwayIds = runwayIds;
  }

  public List<String> getVehiclesPresentIds() {
    return vehiclesPresentIds;
  }

  public void setVehiclesPresentIds(List<String> vehiclesPresentIds) {
    this.vehiclesPresentIds = vehiclesPresentIds;
  }

  public List<String> getPlanesPresentIds() {
    return planesPresentIds;
  }

  public void setPlanesPresentIds(List<String> planesPresentIds) {
    this.planesPresentIds = planesPresentIds;
  }

  public List<String> getRoutesOriginatingFromAirportIds() {
    return routesOriginatingFromAirportIds;
  }

  public void setRoutesOriginatingFromAirportIds(List<String> routesOriginatingFromAirportIds) {
    this.routesOriginatingFromAirportIds = routesOriginatingFromAirportIds;
  }

  public List<String> getRoutesDestinedForAirportIds() {
    return routesDestinedForAirportIds;
  }

  public void setRoutesDestinedForAirportIds(List<String> routesDestinedForAirportIds) {
    this.routesDestinedForAirportIds = routesDestinedForAirportIds;
  }

  public List<String> getCrewMembersPresentIds() {
    return crewMembersPresentIds;
  }

  public void setCrewMembersPresentIds(List<String> crewMembersPresentIds) {
    this.crewMembersPresentIds = crewMembersPresentIds;
  }
}
