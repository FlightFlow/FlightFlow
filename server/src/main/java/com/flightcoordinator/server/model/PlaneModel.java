package com.flightcoordinator.server.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.PlaneAvailability;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "plane")
public class PlaneModel {
  @Id
  private String id;

  @NotBlank(message = "Model cannot be blank")
  private String model;

  @NotBlank(message = "Registration number cannot be blank")
  private String registrationNumber;

  @NotBlank(message = "Passenger capacity cannot be blank")
  @Min(value = 1, message = "Passenger capacity should be greater than '1'")
  private int passengerCapacity;

  @NotBlank(message = "Fuel efficiency cannot be blank")
  @Min(value = 1, message = "Fuel efficiency should be greater than '1'")
  private Float fuelEfficiency;

  @NotBlank(message = "Max flight range cannot be blank")
  @Min(value = 1, message = "Max flight range should be greater than '1'")
  private Float maxFlightRange;

  @NotBlank(message = "Last maintenance cannot be blank")
  private Date lastMaintenance;

  @NotBlank(message = "Total flight hours cannot be blank")
  @Min(value = 0, message = "Total flight hours should be equal or greater than '0'")
  private Float totalFlightHours;

  @NotBlank(message = "Max takeoff weight cannot be blank")
  @Min(value = 1, message = "Max takeoff weight should be greater than '1'")
  private Float maxTakeoffWeight;

  @NotBlank(message = "Shortest runway length required cannot be blank")
  @Min(value = 1, message = "Shortest runway length required should be greater than '1'")
  private Float shortestRunwayLengthRequired;

  @NotBlank(message = "Shortest runway width required cannot be blank")
  @Min(value = 1, message = "Shortest runway width required should be greater than '1'")
  private Float shortestRunwayWidthRequired;

  @NotBlank(message = "Plane status cannot be blank")
  private PlaneAvailability planeStatus = PlaneAvailability.AVAILABLE;

  @NotBlank(message = "Current location status cannot be blank")
  private String currentLocation;

  @NotBlank(message = "Aircratf operator cannot be blank")
  private String aircraftOperator;

  public PlaneModel(String id, String model, String registrationNumber, int passengerCapacity, Float fuelEfficiency,
      Float maxFlightRange, Date lastMaintenance, Float totalFlightHours, Float maxTakeoffWeight,
      Float shortestRunwayLengthRequired, Float shortestRunwayWidthRequired, PlaneAvailability planeStatus,
      String currentLocation, String aircraftOperator) {
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

  // Getter and Setters
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRegistrationNumber() {
    return this.registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public int getPassengerCapacity() {
    return this.passengerCapacity;
  }

  public void setPassengerCapacity(int passengerCapacity) {
    this.passengerCapacity = passengerCapacity;
  }

  public Float getFuelEfficiency() {
    return this.fuelEfficiency;
  }

  public void setFuelEfficiency(Float fuelEfficiency) {
    this.fuelEfficiency = fuelEfficiency;
  }

  public Float getMaxFlightRange() {
    return this.maxFlightRange;
  }

  public void setMaxFlightRange(Float maxFlightRange) {
    this.maxFlightRange = maxFlightRange;
  }

  public Date getLastMaintenance() {
    return this.lastMaintenance;
  }

  public void setLastMaintenance(Date lastMaintenance) {
    this.lastMaintenance = lastMaintenance;
  }

  public Float getTotalFlightHours() {
    return this.totalFlightHours;
  }

  public void setTotalFlightHours(Float totalFlightHours) {
    this.totalFlightHours = totalFlightHours;
  }

  public Float getMaxTakeoffWeight() {
    return this.maxTakeoffWeight;
  }

  public void setMaxTakeoffWeight(Float maxTakeoffWeight) {
    this.maxTakeoffWeight = maxTakeoffWeight;
  }

  public Float getShortestRunwayLengthRequired() {
    return this.shortestRunwayLengthRequired;
  }

  public void setShortestRunwayLengthRequired(Float shortestRunwayLengthRequired) {
    this.shortestRunwayLengthRequired = shortestRunwayLengthRequired;
  }

  public Float getShortestRunwayWidthRequired() {
    return this.shortestRunwayWidthRequired;
  }

  public void setShortestRunwayWidthRequired(Float shortestRunwayWidthRequired) {
    this.shortestRunwayWidthRequired = shortestRunwayWidthRequired;
  }

  public PlaneAvailability getPlaneStatus() {
    return this.planeStatus;
  }

  public void setPlaneStatus(PlaneAvailability planeStatus) {
    this.planeStatus = planeStatus;
  }

  public String getCurrentLocation() {
    return this.currentLocation;
  }

  public void setCurrentLocation(String currentLocation) {
    this.currentLocation = currentLocation;
  }

  public String getAircraftOperator() {
    return this.aircraftOperator;
  }

  public void setAircraftOperator(String aircraftOperator) {
    this.aircraftOperator = aircraftOperator;
  }
}
