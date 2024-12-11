package com.flightcoordinator.server.entity;

import java.util.Date;

import com.flightcoordinator.server.enums.GroundVehicleAvailability;
import com.flightcoordinator.server.enums.GroundVehicleTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vehicle")
public class VehicleEntity {
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

  public VehicleEntity() {
  }

  public VehicleEntity(String id, @NotBlank(message = "Capacity cannot be blank") GroundVehicleTypes type,
      @NotBlank(message = "Capacity cannot be blank") String vehicleCode,
      @NotBlank(message = "Capacity cannot be blank") @Min(value = 1, message = "Capacity should be greater than '1'") Float capacity,
      @NotBlank(message = "Capacity cannot be blank") GroundVehicleAvailability availability,
      @NotBlank(message = "Capacity cannot be blank") Date maintenanceDue) {
    this.id = id;
    this.type = type;
    this.vehicleCode = vehicleCode;
    this.capacity = capacity;
    this.availability = availability;
    this.maintenanceDue = maintenanceDue;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public GroundVehicleTypes getType() {
    return type;
  }

  public void setType(GroundVehicleTypes type) {
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
}
