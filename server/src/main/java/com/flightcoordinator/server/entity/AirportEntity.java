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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((iataCode == null) ? 0 : iataCode.hashCode());
    result = prime * result + ((icaoCode == null) ? 0 : icaoCode.hashCode());
    result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((runways == null) ? 0 : runways.hashCode());
    result = prime * result + ((vehicles == null) ? 0 : vehicles.hashCode());
    result = prime * result + ((planes == null) ? 0 : planes.hashCode());
    result = prime * result + ((originRoutes == null) ? 0 : originRoutes.hashCode());
    result = prime * result + ((destinationRoutes == null) ? 0 : destinationRoutes.hashCode());
    result = prime * result + ((crewMembers == null) ? 0 : crewMembers.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AirportEntity other = (AirportEntity) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (iataCode == null) {
      if (other.iataCode != null)
        return false;
    } else if (!iataCode.equals(other.iataCode))
      return false;
    if (icaoCode == null) {
      if (other.icaoCode != null)
        return false;
    } else if (!icaoCode.equals(other.icaoCode))
      return false;
    if (countryCode == null) {
      if (other.countryCode != null)
        return false;
    } else if (!countryCode.equals(other.countryCode))
      return false;
    if (type != other.type)
      return false;
    if (runways == null) {
      if (other.runways != null)
        return false;
    } else if (!runways.equals(other.runways))
      return false;
    if (vehicles == null) {
      if (other.vehicles != null)
        return false;
    } else if (!vehicles.equals(other.vehicles))
      return false;
    if (planes == null) {
      if (other.planes != null)
        return false;
    } else if (!planes.equals(other.planes))
      return false;
    if (originRoutes == null) {
      if (other.originRoutes != null)
        return false;
    } else if (!originRoutes.equals(other.originRoutes))
      return false;
    if (destinationRoutes == null) {
      if (other.destinationRoutes != null)
        return false;
    } else if (!destinationRoutes.equals(other.destinationRoutes))
      return false;
    if (crewMembers == null) {
      if (other.crewMembers != null)
        return false;
    } else if (!crewMembers.equals(other.crewMembers))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AirportEntity [id=" + id + ", name=" + name + ", iataCode=" + iataCode + ", icaoCode=" + icaoCode
        + ", countryCode=" + countryCode + ", type=" + type + ", runways=" + runways + ", vehicles=" + vehicles
        + ", planes=" + planes + ", originRoutes=" + originRoutes + ", destinationRoutes=" + destinationRoutes
        + ", crewMembers=" + crewMembers + ", getId()=" + getId() + ", getName()=" + getName() + ", getIataCode()="
        + getIataCode() + ", getIcaoCode()=" + getIcaoCode() + ", getCountryCode()=" + getCountryCode() + ", getType()="
        + getType() + ", getRunways()=" + getRunways() + ", getVehicles()=" + getVehicles() + ", getPlanes()="
        + getPlanes() + ", getOriginRoutes()=" + getOriginRoutes() + ", getDestinationRoutes()="
        + getDestinationRoutes() + ", getCrewMembers()=" + getCrewMembers() + ", hashCode()=" + hashCode()
        + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
  }

}
