package com.flightcoordinator.server.entity;

import java.util.Date;

import com.flightcoordinator.server.enums.PlaneAvailability;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "plane_table")
public class PlaneEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "Model cannot be blank")
  @Column(name = "model", nullable = false)
  private String model;

  @NotBlank(message = "Registration number cannot be blank")
  @Column(name = "registration_number", nullable = false)
  private String registrationNumber;

  @Min(value = 1, message = "Passenger capacity should be greater than '1'")
  @Column(name = "passenger_capacity", nullable = false)
  private int passengerCapacity;

  @Min(value = 1, message = "Fuel efficiency should be greater than '1'")
  @Column(name = "fuel_efficiency", nullable = false)
  private Float fuelEfficiency;

  @Min(value = 1, message = "Max flight range should be greater than '1'")
  @Column(name = "max_flight_range", nullable = false)
  private Float maxFlightRange;

  @Column(name = "last_maintenance", nullable = false)
  private Date lastMaintenance;

  @Min(value = 0, message = "Total flight hours should be equal or greater than '0'")
  @Column(name = "total_flight_hours", nullable = false)
  private Float totalFlightHours;

  @Min(value = 1, message = "Max takeoff weight should be greater than '1'")
  @Column(name = "max_takeoff_weight", nullable = false)
  private Float maxTakeoffWeight;

  @Min(value = 1, message = "Shortest runway length required should be greater than '1'")
  @Column(name = "shortest_runway_length_required", nullable = false)
  private Float shortestRunwayLengthRequired;

  @Min(value = 1, message = "Shortest runway width required should be greater than '1'")
  @Column(name = "shortest_runway_width_required", nullable = false)
  private Float shortestRunwayWidthRequired;

  @Enumerated(EnumType.STRING)
  @Column(name = "plane_status", nullable = false)
  private PlaneAvailability planeStatus = PlaneAvailability.AVAILABLE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "current_location_id", nullable = false)
  private AirportEntity currentLocation;

  @NotBlank(message = "Aircraft operator cannot be blank")
  @Column(name = "aircraft_operator", nullable = false)
  private String aircraftOperator;

  public PlaneEntity() {
  }

  public PlaneEntity(String id, @NotBlank(message = "Model cannot be blank") String model,
      @NotBlank(message = "Registration number cannot be blank") String registrationNumber,
      @Min(value = 1, message = "Passenger capacity should be greater than '1'") int passengerCapacity,
      @Min(value = 1, message = "Fuel efficiency should be greater than '1'") Float fuelEfficiency,
      @Min(value = 1, message = "Max flight range should be greater than '1'") Float maxFlightRange,
      Date lastMaintenance,
      @Min(value = 0, message = "Total flight hours should be equal or greater than '0'") Float totalFlightHours,
      @Min(value = 1, message = "Max takeoff weight should be greater than '1'") Float maxTakeoffWeight,
      @Min(value = 1, message = "Shortest runway length required should be greater than '1'") Float shortestRunwayLengthRequired,
      @Min(value = 1, message = "Shortest runway width required should be greater than '1'") Float shortestRunwayWidthRequired,
      PlaneAvailability planeStatus, AirportEntity currentLocation,
      @NotBlank(message = "Aircraft operator cannot be blank") String aircraftOperator) {
    this.id = id;
    this.model = model;
    this.registrationNumber = registrationNumber;
    this.passengerCapacity = passengerCapacity;
    this.fuelEfficiency = fuelEfficiency;
    this.maxFlightRange = maxFlightRange;
    this.lastMaintenance = lastMaintenance;
    this.totalFlightHours = totalFlightHours;
    this.maxTakeoffWeight = maxTakeoffWeight;
    this.shortestRunwayLengthRequired = shortestRunwayLengthRequired;
    this.shortestRunwayWidthRequired = shortestRunwayWidthRequired;
    this.planeStatus = planeStatus;
    this.currentLocation = currentLocation;
    this.aircraftOperator = aircraftOperator;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public int getPassengerCapacity() {
    return passengerCapacity;
  }

  public void setPassengerCapacity(int passengerCapacity) {
    this.passengerCapacity = passengerCapacity;
  }

  public Float getFuelEfficiency() {
    return fuelEfficiency;
  }

  public void setFuelEfficiency(Float fuelEfficiency) {
    this.fuelEfficiency = fuelEfficiency;
  }

  public Float getMaxFlightRange() {
    return maxFlightRange;
  }

  public void setMaxFlightRange(Float maxFlightRange) {
    this.maxFlightRange = maxFlightRange;
  }

  public Date getLastMaintenance() {
    return lastMaintenance;
  }

  public void setLastMaintenance(Date lastMaintenance) {
    this.lastMaintenance = lastMaintenance;
  }

  public Float getTotalFlightHours() {
    return totalFlightHours;
  }

  public void setTotalFlightHours(Float totalFlightHours) {
    this.totalFlightHours = totalFlightHours;
  }

  public Float getMaxTakeoffWeight() {
    return maxTakeoffWeight;
  }

  public void setMaxTakeoffWeight(Float maxTakeoffWeight) {
    this.maxTakeoffWeight = maxTakeoffWeight;
  }

  public Float getShortestRunwayLengthRequired() {
    return shortestRunwayLengthRequired;
  }

  public void setShortestRunwayLengthRequired(Float shortestRunwayLengthRequired) {
    this.shortestRunwayLengthRequired = shortestRunwayLengthRequired;
  }

  public Float getShortestRunwayWidthRequired() {
    return shortestRunwayWidthRequired;
  }

  public void setShortestRunwayWidthRequired(Float shortestRunwayWidthRequired) {
    this.shortestRunwayWidthRequired = shortestRunwayWidthRequired;
  }

  public PlaneAvailability getPlaneStatus() {
    return planeStatus;
  }

  public void setPlaneStatus(PlaneAvailability planeStatus) {
    this.planeStatus = planeStatus;
  }

  public AirportEntity getCurrentLocation() {
    return currentLocation;
  }

  public void setCurrentLocation(AirportEntity currentLocation) {
    this.currentLocation = currentLocation;
  }

  public String getAircraftOperator() {
    return aircraftOperator;
  }

  public void setAircraftOperator(String aircraftOperator) {
    this.aircraftOperator = aircraftOperator;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((model == null) ? 0 : model.hashCode());
    result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
    result = prime * result + passengerCapacity;
    result = prime * result + ((fuelEfficiency == null) ? 0 : fuelEfficiency.hashCode());
    result = prime * result + ((maxFlightRange == null) ? 0 : maxFlightRange.hashCode());
    result = prime * result + ((lastMaintenance == null) ? 0 : lastMaintenance.hashCode());
    result = prime * result + ((totalFlightHours == null) ? 0 : totalFlightHours.hashCode());
    result = prime * result + ((maxTakeoffWeight == null) ? 0 : maxTakeoffWeight.hashCode());
    result = prime * result + ((shortestRunwayLengthRequired == null) ? 0 : shortestRunwayLengthRequired.hashCode());
    result = prime * result + ((shortestRunwayWidthRequired == null) ? 0 : shortestRunwayWidthRequired.hashCode());
    result = prime * result + ((planeStatus == null) ? 0 : planeStatus.hashCode());
    result = prime * result + ((currentLocation == null) ? 0 : currentLocation.hashCode());
    result = prime * result + ((aircraftOperator == null) ? 0 : aircraftOperator.hashCode());
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
    PlaneEntity other = (PlaneEntity) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (model == null) {
      if (other.model != null)
        return false;
    } else if (!model.equals(other.model))
      return false;
    if (registrationNumber == null) {
      if (other.registrationNumber != null)
        return false;
    } else if (!registrationNumber.equals(other.registrationNumber))
      return false;
    if (passengerCapacity != other.passengerCapacity)
      return false;
    if (fuelEfficiency == null) {
      if (other.fuelEfficiency != null)
        return false;
    } else if (!fuelEfficiency.equals(other.fuelEfficiency))
      return false;
    if (maxFlightRange == null) {
      if (other.maxFlightRange != null)
        return false;
    } else if (!maxFlightRange.equals(other.maxFlightRange))
      return false;
    if (lastMaintenance == null) {
      if (other.lastMaintenance != null)
        return false;
    } else if (!lastMaintenance.equals(other.lastMaintenance))
      return false;
    if (totalFlightHours == null) {
      if (other.totalFlightHours != null)
        return false;
    } else if (!totalFlightHours.equals(other.totalFlightHours))
      return false;
    if (maxTakeoffWeight == null) {
      if (other.maxTakeoffWeight != null)
        return false;
    } else if (!maxTakeoffWeight.equals(other.maxTakeoffWeight))
      return false;
    if (shortestRunwayLengthRequired == null) {
      if (other.shortestRunwayLengthRequired != null)
        return false;
    } else if (!shortestRunwayLengthRequired.equals(other.shortestRunwayLengthRequired))
      return false;
    if (shortestRunwayWidthRequired == null) {
      if (other.shortestRunwayWidthRequired != null)
        return false;
    } else if (!shortestRunwayWidthRequired.equals(other.shortestRunwayWidthRequired))
      return false;
    if (planeStatus != other.planeStatus)
      return false;
    if (currentLocation == null) {
      if (other.currentLocation != null)
        return false;
    } else if (!currentLocation.equals(other.currentLocation))
      return false;
    if (aircraftOperator == null) {
      if (other.aircraftOperator != null)
        return false;
    } else if (!aircraftOperator.equals(other.aircraftOperator))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PlaneEntity [id=" + id + ", model=" + model + ", registrationNumber=" + registrationNumber
        + ", passengerCapacity=" + passengerCapacity + ", fuelEfficiency=" + fuelEfficiency + ", maxFlightRange="
        + maxFlightRange + ", lastMaintenance=" + lastMaintenance + ", totalFlightHours=" + totalFlightHours
        + ", maxTakeoffWeight=" + maxTakeoffWeight + ", shortestRunwayLengthRequired=" + shortestRunwayLengthRequired
        + ", shortestRunwayWidthRequired=" + shortestRunwayWidthRequired + ", planeStatus=" + planeStatus
        + ", currentLocation=" + currentLocation + ", aircraftOperator=" + aircraftOperator + ", getId()=" + getId()
        + ", getModel()=" + getModel() + ", getRegistrationNumber()=" + getRegistrationNumber()
        + ", getPassengerCapacity()=" + getPassengerCapacity() + ", getFuelEfficiency()=" + getFuelEfficiency()
        + ", getMaxFlightRange()=" + getMaxFlightRange() + ", getLastMaintenance()=" + getLastMaintenance()
        + ", getTotalFlightHours()=" + getTotalFlightHours() + ", getMaxTakeoffWeight()=" + getMaxTakeoffWeight()
        + ", getShortestRunwayLengthRequired()=" + getShortestRunwayLengthRequired()
        + ", getShortestRunwayWidthRequired()=" + getShortestRunwayWidthRequired() + ", getPlaneStatus()="
        + getPlaneStatus() + ", getCurrentLocation()=" + getCurrentLocation() + ", getAircraftOperator()="
        + getAircraftOperator() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
        + super.toString() + "]";
  }

  
}


