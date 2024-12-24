package com.flightcoordinator.server.entity;

import java.util.Date;

import com.flightcoordinator.server.enums.GroundVehicleAvailability;
import com.flightcoordinator.server.enums.GroundVehicleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "vehicle_table")
public class VehicleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "type", nullable = false)
  private GroundVehicleType type;

  @Column(name = "vehicle_code", nullable = false)
  private String vehicleCode;

  @Min(value = 1, message = "Capacity should be greater than '1'")
  @Column(name = "capacity", nullable = false)
  private Float capacity;

  @Enumerated(EnumType.STRING)
  @Column(name = "availability", nullable = false)
  private GroundVehicleAvailability availability;

  @Column(name = "maintenance_due", nullable = false)
  private Date maintenanceDue;

  @ManyToOne
  @JoinColumn(name = "airport_id", nullable = false)
  private AirportEntity airport;

  public VehicleEntity() {
  }

  public VehicleEntity(String id, GroundVehicleType type, String vehicleCode,
      @Min(value = 1, message = "Capacity should be greater than '1'") Float capacity,
      GroundVehicleAvailability availability, Date maintenanceDue) {
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

  public AirportEntity getAirport() {
    return airport;
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

  public void setAirport(AirportEntity airport) {
    this.airport = airport;
  }
}
