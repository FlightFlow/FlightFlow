package com.flightcoordinator.server.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.flightcoordinator.server.enums.AirportType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "airport_table")
public class AirportEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "iata_code", nullable = false, unique = true)
  private String iataCode;

  @Column(name = "icao_code", nullable = false, unique = true)
  private String icaoCode;

  @Column(name = "country_code", nullable = false)
  private String countryCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private AirportType type;

  // A list of runways for the airport
  @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RunwayEntity> runways = new ArrayList<>();

  // One-to-many relationship with VehicleEntity
  @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<VehicleEntity> vehicles;

  // A list of planes currently located at the airport
  @OneToMany(mappedBy = "currentLocation", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PlaneEntity> planes = new ArrayList<>();

  // Routes where the airport is the origin
  @OneToMany(mappedBy = "originAirport", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RouteEntity> originRoutes = new ArrayList<>();

  // Routes where the airport is the destination
  @OneToMany(mappedBy = "destinationAirport", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RouteEntity> destinationRoutes = new ArrayList<>();

  // Crew members assigned to this airport
  @OneToMany(mappedBy = "baseAirport", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CrewEntity> crewMembers = new ArrayList<>();

  public AirportEntity() {
  }

  public AirportEntity(String id, String name, String iataCode, String icaoCode, String countryCode, AirportType type,
      List<RunwayEntity> runways, Set<VehicleEntity> vehicles, List<PlaneEntity> planes,
      List<RouteEntity> originRoutes, List<RouteEntity> destinationRoutes, List<CrewEntity> crewMembers) {
    this.id = id;
    this.name = name;
    this.iataCode = iataCode;
    this.icaoCode = icaoCode;
    this.countryCode = countryCode;
    this.type = type;
    this.runways = runways;
    this.vehicles = vehicles;
    this.planes = planes;
    this.originRoutes = originRoutes;
    this.destinationRoutes = destinationRoutes;
    this.crewMembers = crewMembers;
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

  public List<RunwayEntity> getRunways() {
    return runways;
  }

  public void setRunways(List<RunwayEntity> runways) {
    this.runways = runways;
  }

  public Set<VehicleEntity> getVehicles() {
    return vehicles;
  }

  public void setVehicles(Set<VehicleEntity> vehicles) {
    this.vehicles = vehicles;
  }

  public List<PlaneEntity> getPlanes() {
    return planes;
  }

  public void setPlanes(List<PlaneEntity> planes) {
    this.planes = planes;
  }

  public List<RouteEntity> getOriginRoutes() {
    return originRoutes;
  }

  public void setOriginRoutes(List<RouteEntity> originRoutes) {
    this.originRoutes = originRoutes;
  }

  public List<RouteEntity> getDestinationRoutes() {
    return destinationRoutes;
  }

  public void setDestinationRoutes(List<RouteEntity> destinationRoutes) {
    this.destinationRoutes = destinationRoutes;
  }

  public List<CrewEntity> getCrewMembers() {
    return crewMembers;
  }

  public void setCrewMembers(List<CrewEntity> crewMembers) {
    this.crewMembers = crewMembers;
  }
}
