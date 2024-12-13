package com.flightcoordinator.server.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.flightcoordinator.server.enums.PlaneAvailability;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

  @NotBlank(message = "Passenger capacity cannot be blank")
  @Min(value = 1, message = "Passenger capacity should be greater than '1'")
  @Column(name = "passenger_capacity", nullable = false)
  private int passengerCapacity;

  @NotBlank(message = "Fuel efficiency cannot be blank")
  @Min(value = 1, message = "Fuel efficiency should be greater than '1'")
  @Column(name = "fuel_efficiency", nullable = false)
  private Float fuelEfficiency;

  @NotBlank(message = "Max flight range cannot be blank")
  @Min(value = 1, message = "Max flight range should be greater than '1'")
  @Column(name = "max_flight_range", nullable = false)
  private Float maxFlightRange;

  @NotBlank(message = "Last maintenance cannot be blank")
  @Column(name = "last_maintenance", nullable = false)
  private Date lastMaintenance;

  @NotBlank(message = "Total flight hours cannot be blank")
  @Min(value = 0, message = "Total flight hours should be equal or greater than '0'")
  @Column(name = "total_flight_hours", nullable = false)
  private Float totalFlightHours;

  @NotBlank(message = "Max takeoff weight cannot be blank")
  @Min(value = 1, message = "Max takeoff weight should be greater than '1'")
  @Column(name = "max_takeoff_weight", nullable = false)
  private Float maxTakeoffWeight;

  @NotBlank(message = "Shortest runway length required cannot be blank")
  @Min(value = 1, message = "Shortest runway length required should be greater than '1'")
  @Column(name = "shortest_runway_length_required", nullable = false)
  private Float shortestRunwayLengthRequired;

  @NotBlank(message = "Shortest runway width required cannot be blank")
  @Min(value = 1, message = "Shortest runway width required should be greater than '1'")
  @Column(name = "shortest_runway_width_required", nullable = false)
  private Float shortestRunwayWidthRequired;

  @NotBlank(message = "Plane status cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "plane_status", nullable = false)
  private PlaneAvailability planeStatus = PlaneAvailability.AVAILABLE;

  @NotBlank(message = "Current location status cannot be blank")
  @ManyToOne
  @JoinColumn(name = "current_location", nullable = false)
  private AirportEntity currentLocation;

  @NotBlank(message = "Aircratf operator cannot be blank")
  @Column(name = "aircraft_operator", nullable = false)
  private String aircraftOperator;

  public PlaneEntity() {
  }

  public PlaneEntity(String id, @NotBlank(message = "Model cannot be blank") String model,
      @NotBlank(message = "Registration number cannot be blank") String registrationNumber,
      @NotBlank(message = "Passenger capacity cannot be blank") @Min(value = 1, message = "Passenger capacity should be greater than '1'") int passengerCapacity,
      @NotBlank(message = "Fuel efficiency cannot be blank") @Min(value = 1, message = "Fuel efficiency should be greater than '1'") Float fuelEfficiency,
      @NotBlank(message = "Max flight range cannot be blank") @Min(value = 1, message = "Max flight range should be greater than '1'") Float maxFlightRange,
      @NotBlank(message = "Last maintenance cannot be blank") Date lastMaintenance,
      @NotBlank(message = "Total flight hours cannot be blank") @Min(value = 0, message = "Total flight hours should be equal or greater than '0'") Float totalFlightHours,
      @NotBlank(message = "Max takeoff weight cannot be blank") @Min(value = 1, message = "Max takeoff weight should be greater than '1'") Float maxTakeoffWeight,
      @NotBlank(message = "Shortest runway length required cannot be blank") @Min(value = 1, message = "Shortest runway length required should be greater than '1'") Float shortestRunwayLengthRequired,
      @NotBlank(message = "Shortest runway width required cannot be blank") @Min(value = 1, message = "Shortest runway width required should be greater than '1'") Float shortestRunwayWidthRequired,
      @NotBlank(message = "Plane status cannot be blank") PlaneAvailability planeStatus,
      @NotBlank(message = "Current location status cannot be blank") AirportEntity currentLocation,
      @NotBlank(message = "Aircratf operator cannot be blank") String aircraftOperator) {
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
}
