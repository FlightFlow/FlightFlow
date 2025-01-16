package com.flightcoordinator.server.entity;

import java.util.List;

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
  @Column(name = "id")
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

  @OneToMany(mappedBy = "airport", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<RunwayEntity> runways;

  @OneToMany(mappedBy = "airport", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private List<VehicleEntity> vehiclesPresent;

  @OneToMany(mappedBy = "currentLocation", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<PlaneEntity> planesPresent;

  @OneToMany(mappedBy = "originAirport", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<RouteEntity> routesOriginatingFromAirport;

  @OneToMany(mappedBy = "destinationAirport", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<RouteEntity> routesDestinedForAirport;

  @OneToMany(mappedBy = "baseAirport", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private List<CrewEntity> crewMembersPresent;

  public AirportEntity() {
  }

  public AirportEntity(String id, String name, String iataCode, String icaoCode, String countryCode, AirportType type,
      List<RunwayEntity> runways, List<VehicleEntity> vehiclesPresent, List<PlaneEntity> planesPresent,
      List<RouteEntity> routesOriginatingFromAirport, List<RouteEntity> routesDestinedForAirport,
      List<CrewEntity> crewMembersPresent) {
    this.id = id;
    this.name = name;
    this.iataCode = iataCode;
    this.icaoCode = icaoCode;
    this.countryCode = countryCode;
    this.type = type;
    this.runways = runways;
    this.vehiclesPresent = vehiclesPresent;
    this.planesPresent = planesPresent;
    this.routesOriginatingFromAirport = routesOriginatingFromAirport;
    this.routesDestinedForAirport = routesDestinedForAirport;
    this.crewMembersPresent = crewMembersPresent;
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

  public List<VehicleEntity> getVehiclesPresent() {
    return vehiclesPresent;
  }

  public void setVehiclesPresent(List<VehicleEntity> vehiclesPresent) {
    this.vehiclesPresent = vehiclesPresent;
  }

  public List<PlaneEntity> getPlanesPresent() {
    return planesPresent;
  }

  public void setPlanesPresent(List<PlaneEntity> planesPresent) {
    this.planesPresent = planesPresent;
  }

  public List<RouteEntity> getRoutesOriginatingFromAirport() {
    return routesOriginatingFromAirport;
  }

  public void setRoutesOriginatingFromAirport(List<RouteEntity> routesOriginatingFromAirport) {
    this.routesOriginatingFromAirport = routesOriginatingFromAirport;
  }

  public List<RouteEntity> getRoutesDestinedForAirport() {
    return routesDestinedForAirport;
  }

  public void setRoutesDestinedForAirport(List<RouteEntity> routesDestinedForAirport) {
    this.routesDestinedForAirport = routesDestinedForAirport;
  }

  public List<CrewEntity> getCrewMembersPresent() {
    return crewMembersPresent;
  }

  public void setCrewMembersPresent(List<CrewEntity> crewMembersPresent) {
    this.crewMembersPresent = crewMembersPresent;
  }
}
