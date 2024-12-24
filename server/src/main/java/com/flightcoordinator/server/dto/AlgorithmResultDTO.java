package com.flightcoordinator.server.dto;

import java.util.List;

public class AlgorithmResultDTO {
  private String id;
  private String flightId;
  private String planeId;
  private List<String> crewMemberIds;
  private String takeoffRunwayId;
  private String landingRunwayId;
  private List<String> originAirportGroundVehicleIds;
  private List<String> destinationAirportGroundVehicleIds;

  public AlgorithmResultDTO() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFlightId() {
    return flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getPlaneId() {
    return planeId;
  }

  public void setPlaneId(String planeId) {
    this.planeId = planeId;
  }

  public List<String> getCrewMemberIds() {
    return crewMemberIds;
  }

  public void setCrewMemberIds(List<String> crewMemberIds) {
    this.crewMemberIds = crewMemberIds;
  }

  public String getTakeoffRunwayId() {
    return takeoffRunwayId;
  }

  public void setTakeoffRunwayId(String takeoffRunwayId) {
    this.takeoffRunwayId = takeoffRunwayId;
  }

  public String getLandingRunwayId() {
    return landingRunwayId;
  }

  public void setLandingRunwayId(String landingRunwayId) {
    this.landingRunwayId = landingRunwayId;
  }

  public List<String> getOriginAirportGroundVehicleIds() {
    return originAirportGroundVehicleIds;
  }

  public void setOriginAirportGroundVehicleIds(List<String> originAirportGroundVehicleIds) {
    this.originAirportGroundVehicleIds = originAirportGroundVehicleIds;
  }

  public List<String> getDestinationAirportGroundVehicleIds() {
    return destinationAirportGroundVehicleIds;
  }

  public void setDestinationAirportGroundVehicleIds(List<String> destinationAirportGroundVehicleIds) {
    this.destinationAirportGroundVehicleIds = destinationAirportGroundVehicleIds;
  }
}
