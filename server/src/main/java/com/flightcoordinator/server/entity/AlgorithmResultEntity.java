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

    // Crew ilişkisi: Bir AlgorithmResultEntity'ye birden fazla mürettebat atanabilir
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "algorithm_result_crew_members", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "crew_member_id"))
    private List<CrewEntity> crewMembers = new ArrayList<>();

    // Takeoff Runway ilişkisi: Her AlgorithmResultEntity'ye bir takeoff runway atanabilir
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "takeoff_runway_id", nullable = false)
    private RunwayEntity takeoffRunway;

    // Landing Runway ilişkisi: Her AlgorithmResultEntity'ye bir landing runway atanabilir
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landing_runway_id", nullable = false)
    private RunwayEntity landingRunway;

    // Origin Ground Vehicles ilişkisi: Bir AlgorithmResultEntity'ye birden fazla ground vehicle atanabilir
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "algorithm_result_ground_vehicles_origin", joinColumns = @JoinColumn(name = "algorithm_result_id"), inverseJoinColumns = @JoinColumn(name = "ground_vehicle_id"))
    private List<VehicleEntity> originAirportGroundVehicles = new ArrayList<>();

    // Destination Ground Vehicles ilişkisi: Bir AlgorithmResultEntity'ye birden fazla ground vehicle atanabilir
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
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((flight == null) ? 0 : flight.hashCode());
      result = prime * result + ((plane == null) ? 0 : plane.hashCode());
      result = prime * result + ((crewMembers == null) ? 0 : crewMembers.hashCode());
      result = prime * result + ((takeoffRunway == null) ? 0 : takeoffRunway.hashCode());
      result = prime * result + ((landingRunway == null) ? 0 : landingRunway.hashCode());
      result = prime * result + ((originAirportGroundVehicles == null) ? 0 : originAirportGroundVehicles.hashCode());
      result = prime * result
          + ((destinationAirportGroundVehicles == null) ? 0 : destinationAirportGroundVehicles.hashCode());
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
      AlgorithmResultEntity other = (AlgorithmResultEntity) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      if (flight == null) {
        if (other.flight != null)
          return false;
      } else if (!flight.equals(other.flight))
        return false;
      if (plane == null) {
        if (other.plane != null)
          return false;
      } else if (!plane.equals(other.plane))
        return false;
      if (crewMembers == null) {
        if (other.crewMembers != null)
          return false;
      } else if (!crewMembers.equals(other.crewMembers))
        return false;
      if (takeoffRunway == null) {
        if (other.takeoffRunway != null)
          return false;
      } else if (!takeoffRunway.equals(other.takeoffRunway))
        return false;
      if (landingRunway == null) {
        if (other.landingRunway != null)
          return false;
      } else if (!landingRunway.equals(other.landingRunway))
        return false;
      if (originAirportGroundVehicles == null) {
        if (other.originAirportGroundVehicles != null)
          return false;
      } else if (!originAirportGroundVehicles.equals(other.originAirportGroundVehicles))
        return false;
      if (destinationAirportGroundVehicles == null) {
        if (other.destinationAirportGroundVehicles != null)
          return false;
      } else if (!destinationAirportGroundVehicles.equals(other.destinationAirportGroundVehicles))
        return false;
      return true;
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
