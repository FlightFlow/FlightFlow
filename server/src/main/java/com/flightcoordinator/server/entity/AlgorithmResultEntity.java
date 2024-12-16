package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "algorithm_result_table")
public class AlgorithmResultEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  // Flight ilişkisi: Bir AlgorithmResultEntity'ye bir uçuş atanabilir
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id", nullable = false)
  private FlightEntity flight;

  // Plane ilişkisi: Bir AlgorithmResultEntity'ye bir uçak atanabilir
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plane_id", nullable = false)
  private PlaneEntity plane;

  // Crew ilişkisi: Bir AlgorithmResultEntity'ye birden fazla mürettebat
  // atanabilir
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_crew_members", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "crew_member_id"))
  private List<CrewEntity> crewMembers = new ArrayList<>();

  // Takeoff Runway ilişkisi: Her AlgorithmResultEntity'ye bir takeoff runway
  // atanabilir
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "takeoff_runway_id", nullable = false)
  private RunwayEntity takeoffRunway;

  // Landing Runway ilişkisi: Her AlgorithmResultEntity'ye bir landing runway
  // atanabilir
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "landing_runway_id", nullable = false)
  private RunwayEntity landingRunway;

  // Origin Ground Vehicles ilişkisi: Bir AlgorithmResultEntity'ye birden fazla
  // ground vehicle atanabilir
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_ground_vehicles_origin", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
  private List<VehicleEntity> originAirportGroundVehicles = new ArrayList<>();

  // Destination Ground Vehicles ilişkisi: Bir AlgorithmResultEntity'ye birden
  // fazla ground vehicle atanabilir
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "algorithm_result_ground_vehicles_destination", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
  private List<VehicleEntity> destinationAirportGroundVehicles = new ArrayList<>();

  public AlgorithmResultEntity() {
  }

  public AlgorithmResultEntity(String id, FlightEntity flight, PlaneEntity plane, List<CrewEntity> crewMembers,
      RunwayEntity takeoffRunway, RunwayEntity landingRunway, List<VehicleEntity> originAirportGroundVehicles,
      List<VehicleEntity> destinationAirportGroundVehicles) {
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

  @Override
  public String toString() {
    return "AlgorithmResultEntity [id=" + id + ", flight=" + flight + ", plane=" + plane + ", crewMembers="
        + crewMembers + ", takeoffRunway=" + takeoffRunway + ", landingRunway=" + landingRunway
        + ", originAirportGroundVehicles=" + originAirportGroundVehicles + ", destinationAirportGroundVehicles="
        + destinationAirportGroundVehicles + ", getId()=" + getId() + ", getFlight()=" + getFlight() + ", getPlane()="
        + getPlane() + ", getCrewMembers()=" + getCrewMembers() + ", getTakeoffRunway()=" + getTakeoffRunway()
        + ", getLandingRunway()=" + getLandingRunway() + ", getOriginAirportGroundVehicles()="
        + getOriginAirportGroundVehicles() + ", getDestinationAirportGroundVehicles()="
        + getDestinationAirportGroundVehicles() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
        + ", toString()=" + super.toString() + "]";
  }
}
