package com.flightcoordinator.server.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "algorithm_result_table")
public class AlgorithmResultEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "route_id", nullable = false)
  @NotBlank(message = "Route ID cannot be blank")
  private RouteEntity route;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plane_id", nullable = false)
  @NotBlank(message = "Plane ID cannot be blank")
  private PlaneEntity plane;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_crew_members", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "crew_member_id"))
  @NotBlank(message = "Crew members list cannot be blank")
  private List<CrewEntity> crewMembers;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_runway", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
  @NotBlank(message = "Runways list cannot be blank")
  private List<RunwayEntity> runways;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_ground_vehicles", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
  @NotBlank(message = "Ground vehicles list cannot be blank")
  private List<VehicleEntity> groundVehicles;

  public AlgorithmResultEntity() {
  }

  public AlgorithmResultEntity(String id, @NotBlank(message = "Route ID cannot be blank") RouteEntity route,
      @NotBlank(message = "Plane ID cannot be blank") PlaneEntity plane,
      @NotBlank(message = "Crew members list cannot be blank") List<CrewEntity> crewMembers,
      @NotBlank(message = "Runways list cannot be blank") List<RunwayEntity> runways,
      @NotBlank(message = "Ground vehicles list cannot be blank") List<VehicleEntity> groundVehicles) {
    this.id = id;
    this.route = route;
    this.plane = plane;
    this.crewMembers = crewMembers;
    this.runways = runways;
    this.groundVehicles = groundVehicles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RouteEntity getRoute() {
    return route;
  }

  public void setRoute(RouteEntity route) {
    this.route = route;
  }

  public PlaneEntity getPlane() {
    return plane;
  }

  public void setPlane(PlaneEntity plane) {
    this.plane = plane;
  }

  public List<CrewEntity> getCrewMembers() {
    return crewMembers;
  }

  public void setCrewMembers(List<CrewEntity> crewMembers) {
    this.crewMembers = crewMembers;
  }

  public List<RunwayEntity> getRunways() {
    return runways;
  }

  public void setRunways(List<RunwayEntity> runways) {
    this.runways = runways;
  }

  public List<VehicleEntity> getGroundVehicles() {
    return groundVehicles;
  }

  public void setGroundVehicles(List<VehicleEntity> groundVehicles) {
    this.groundVehicles = groundVehicles;
  }
}
