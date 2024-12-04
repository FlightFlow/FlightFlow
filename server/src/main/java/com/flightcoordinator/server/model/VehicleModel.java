package com.flightcoordinator.server.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.GroundVehicleAvailability;
import com.flightcoordinator.server.enums.GroundVehicleTypes;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "vehicle")
public class VehicleModel {
  @Id
  private String id;

  @NotBlank(message = "Capacity cannot be blank")
  private GroundVehicleTypes type;

  @NotBlank(message = "Capacity cannot be blank")
  private String vehicleCode;

  @NotBlank(message = "Capacity cannot be blank")
  @Min(value = 1, message = "Capacity should be greater than '1'")
  private Float capacity;

  @NotBlank(message = "Capacity cannot be blank")
  private GroundVehicleAvailability availability = GroundVehicleAvailability.AVAILABLE;

  @NotBlank(message = "Capacity cannot be blank")
  private Date maintenanceDue;

  public VehicleModel(String id, GroundVehicleTypes type, String vehicleCode, Float capacity,
      GroundVehicleAvailability availability, Date maintenanceDue) {
    this.id = id;
    this.type = type;
    this.vehicleCode = vehicleCode;
    this.capacity = capacity;
    this.availability = availability;
    this.maintenanceDue = maintenanceDue;
  }

  // Getter and Setters
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public GroundVehicleTypes getType() {
    return this.type;
  }

  public void setType(GroundVehicleTypes type) {
    this.type = type;
  }

  public String getVehicleCode() {
    return this.vehicleCode;
  }

  public void setVehicleCode(String vehicleCode) {
    this.vehicleCode = vehicleCode;
  }

  public Float getCapacity() {
    return this.capacity;
  }

  public void setCapacity(Float capacity) {
    this.capacity = capacity;
  }

  public GroundVehicleAvailability getAvailability() {
    return this.availability;
  }

  public void setAvailability(GroundVehicleAvailability availability) {
    this.availability = availability;
  }

  public Date getMaintenanceDue() {
    return this.maintenanceDue;
  }

  public void setMaintenanceDue(Date maintenanceDue) {
    this.maintenanceDue = maintenanceDue;
  }

}
