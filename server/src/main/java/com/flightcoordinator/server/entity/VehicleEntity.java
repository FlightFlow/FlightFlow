package com.flightcoordinator.server.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.flightcoordinator.server.enums.GroundVehicleAvailability;
import com.flightcoordinator.server.enums.GroundVehicleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "vehicle_table")
public class VehicleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "Capacity cannot be blank")
  @Column(name = "type", nullable = false)
  private GroundVehicleType type;

  @NotBlank(message = "Capacity cannot be blank")
  @Column(name = "vehicle_code", nullable = false)
  private String vehicleCode;

  @NotBlank(message = "Capacity cannot be blank")
  @Min(value = 1, message = "Capacity should be greater than '1'")
  @Column(name = "capacity", nullable = false)
  private Float capacity;

  @NotBlank(message = "Capacity cannot be blank")
  @Enumerated(EnumType.STRING)
  @Column(name = "availability", nullable = false)
  private GroundVehicleAvailability availability = GroundVehicleAvailability.AVAILABLE;

  @Column(name = "maintenance_due", nullable = false)
  @NotBlank(message = "Capacity cannot be blank")
  private Date maintenanceDue;

  public VehicleEntity() {
  }

  public VehicleEntity(String id, @NotBlank(message = "Capacity cannot be blank") GroundVehicleType type,
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
}
