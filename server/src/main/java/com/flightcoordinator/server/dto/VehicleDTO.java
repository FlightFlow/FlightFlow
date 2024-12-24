package com.flightcoordinator.server.dto;

import java.util.Date;

import com.flightcoordinator.server.enums.GroundVehicleAvailability;
import com.flightcoordinator.server.enums.GroundVehicleType;

public class VehicleDTO {
  private String id;
  private GroundVehicleType type;
  private String vehicleCode;
  private Float capacity;
  private GroundVehicleAvailability availability;
  private Date maintenanceDue;
  private String airportId;

  public VehicleDTO() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public GroundVehicleType getType() {
    return type;
  }

  public void setType(GroundVehicleType type) {
    this.type = type;
  }

  public String getVehicleCode() {
    return vehicleCode;
  }

  public void setVehicleCode(String vehicleCode) {
    this.vehicleCode = vehicleCode;
  }

  public Float getCapacity() {
    return capacity;
  }

  public void setCapacity(Float capacity) {
    this.capacity = capacity;
  }

  public GroundVehicleAvailability getAvailability() {
    return availability;
  }

  public void setAvailability(GroundVehicleAvailability availability) {
    this.availability = availability;
  }

  public Date getMaintenanceDue() {
    return maintenanceDue;
  }

  public void setMaintenanceDue(Date maintenanceDue) {
    this.maintenanceDue = maintenanceDue;
  }

  public String getAirportId() {
    return airportId;
  }

  public void setAirportId(String airportId) {
    this.airportId = airportId;
  }
}
