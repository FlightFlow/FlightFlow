package com.flightcoordinator.server.entity;

import com.flightcoordinator.server.enums.RunwaySurfaceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "runway_table")
public class RunwayEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "length", nullable = false)
  private Float length;

  @Column(name = "width", nullable = false)
  private Float width;

  @Column(name = "surface_type", nullable = false)
  private RunwaySurfaceType surfaceType;

  @Min(value = 1, message = "Max weight capacity should be greater than '1'")
  @Column(name = "max_weight_capacity", nullable = false)
  private Float maxWeightCapacity;

  @Column(name = "orientation", nullable = false)
  private String orientation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "airport_id", nullable = false)
  private AirportEntity airport;

  public RunwayEntity() {
  }

  public RunwayEntity(String id, Float length, Float width, RunwaySurfaceType surfaceType,
      @Min(value = 1, message = "Max weight capacity should be greater than '1'") Float maxWeightCapacity,
      String orientation, AirportEntity airport) {
    this.id = id;
    this.length = length;
    this.width = width;
    this.surfaceType = surfaceType;
    this.maxWeightCapacity = maxWeightCapacity;
    this.orientation = orientation;
    this.airport = airport;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Float getLength() {
    return length;
  }

  public void setLength(Float length) {
    this.length = length;
  }

  public Float getWidth() {
    return width;
  }

  public void setWidth(Float width) {
    this.width = width;
  }

  public RunwaySurfaceType getSurfaceType() {
    return surfaceType;
  }

  public void setSurfaceType(RunwaySurfaceType surfaceType) {
    this.surfaceType = surfaceType;
  }

  public Float getMaxWeightCapacity() {
    return maxWeightCapacity;
  }

  public void setMaxWeightCapacity(Float maxWeightCapacity) {
    this.maxWeightCapacity = maxWeightCapacity;
  }

  public String getOrientation() {
    return orientation;
  }

  public void setOrientation(String orientation) {
    this.orientation = orientation;
  }

  public AirportEntity getAirport() {
    return airport;
  }

  public void setAirport(AirportEntity airport) {
    this.airport = airport;
  }
}
