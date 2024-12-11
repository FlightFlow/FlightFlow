package com.flightcoordinator.server.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "algorithm_results")
public class AlgorithmResultEntity {
  @Id
  private String id;

  @Field("route_id")
  @NotBlank(message = "Route ID cannot be blank")
  private String routeId;

  @Field("plane_id")
  @NotBlank(message = "Plane ID cannot be blank")
  private String planeId;

  @Field("crew_member_ids")
  @NotBlank(message = "Crew member IDs cannot be blank")
  private List<String> crewMemberIds;

  @Field("runway_id")
  @NotBlank(message = "Runway ID cannot be blank")
  private String runwayId;

  @Field("ground_vehicle_ids")
  @NotBlank(message = "Ground vehicle IDs cannot be blank")
  private List<String> groundVehicleIds;

  public AlgorithmResultEntity() {
  }

  public AlgorithmResultEntity(String id, @NotBlank(message = "Route ID cannot be blank") String routeId,
      @NotBlank(message = "Plane ID cannot be blank") String planeId,
      @NotBlank(message = "Crew member IDs cannot be blank") List<String> crewMemberIds,
      @NotBlank(message = "Runway ID cannot be blank") String runwayId,
      @NotBlank(message = "Ground vehicle IDs cannot be blank") List<String> groundVehicleIds) {
    this.id = id;
    this.routeId = routeId;
    this.planeId = planeId;
    this.crewMemberIds = crewMemberIds;
    this.runwayId = runwayId;
    this.groundVehicleIds = groundVehicleIds;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
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

  public String getRunwayId() {
    return runwayId;
  }

  public void setRunwayId(String runwayId) {
    this.runwayId = runwayId;
  }

  public List<String> getGroundVehicleIds() {
    return groundVehicleIds;
  }

  public void setGroundVehicleIds(List<String> groundVehicleIds) {
    this.groundVehicleIds = groundVehicleIds;
  }
}
