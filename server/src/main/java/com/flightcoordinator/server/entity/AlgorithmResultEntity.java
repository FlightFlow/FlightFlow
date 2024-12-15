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
  @JoinColumn(name = "flight_id", nullable = false)
  @NotBlank(message = "Flight ID cannot be blank")
  private FlightEntity flight;

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
  private RunwayEntity takeoffRunway;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_runway", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
  @NotBlank(message = "Runways list cannot be blank")
  private RunwayEntity landingRunway;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_ground_vehicles", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
  @NotBlank(message = "Ground vehicles list cannot be blank")
  private List<VehicleEntity> originAirportGroundVehicles;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_ground_vehicles", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
  @NotBlank(message = "Ground vehicles list cannot be blank")
  private List<VehicleEntity> destinationAirportGroundVehicles;

  public AlgorithmResultEntity() {
  }

  public AlgorithmResultEntity(String id, @NotBlank(message = "Flight ID cannot be blank") FlightEntity flight,
      @NotBlank(message = "Plane ID cannot be blank") PlaneEntity plane,
      @NotBlank(message = "Crew members list cannot be blank") List<CrewEntity> crewMembers,
      @NotBlank(message = "Runways list cannot be blank") RunwayEntity takeoffRunway,
      @NotBlank(message = "Runways list cannot be blank") RunwayEntity landingRunway,
      @NotBlank(message = "Ground vehicles list cannot be blank") List<VehicleEntity> originAirportGroundVehicles,
      @NotBlank(message = "Ground vehicles list cannot be blank") List<VehicleEntity> destinationAirportGroundVehicles) {
    this.id = id;
    this.flight = flight;
    this.plane = plane;
    this.crewMembers = crewMembers;
    this.takeoffRunway = takeoffRunway;
    this.landingRunway = landingRunway;
    this.originAirportGroundVehicles = originAirportGroundVehicles;
    this.destinationAirportGroundVehicles = destinationAirportGroundVehicles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public FlightEntity getFlight() {
    return flight;
  }

  public void setFlight(FlightEntity flight) {
    this.flight = flight;
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

  public RunwayEntity getTakeoffRunway() {
    return takeoffRunway;
  }

  public void setTakeoffRunway(RunwayEntity takeoffRunway) {
    this.takeoffRunway = takeoffRunway;
  }

  public RunwayEntity getLandingRunway() {
    return landingRunway;
  }

  public void setLandingRunway(RunwayEntity landingRunway) {
    this.landingRunway = landingRunway;
  }

  public List<VehicleEntity> getOriginAirportGroundVehicles() {
    return originAirportGroundVehicles;
  }

  public void setOriginAirportGroundVehicles(List<VehicleEntity> originAirportGroundVehicles) {
    this.originAirportGroundVehicles = originAirportGroundVehicles;
  }

  public List<VehicleEntity> getDestinationAirportGroundVehicles() {
    return destinationAirportGroundVehicles;
  }

  public void setDestinationAirportGroundVehicles(List<VehicleEntity> destinationAirportGroundVehicles) {
    this.destinationAirportGroundVehicles = destinationAirportGroundVehicles;
  }
}
