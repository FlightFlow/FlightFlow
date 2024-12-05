package com.flightcoordinator.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.RunwaySurfaceTypes;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "runway")
public class RunwayModel {
  @Id
  private String id;

  @NotBlank(message = "Length cannot be blank")
  private Float length;

  @NotBlank(message = "Width cannot be blank")
  private Float width;

  @NotBlank(message = "Surface type cannot be blank")
  private RunwaySurfaceTypes surfaceType;

  @NotBlank(message = "Max weigth capacity cannot be blank")
  @Min(value = 1, message = "Max weight capacity should be greater than '1'")
  private Float maxWeightCapacity;

  @NotBlank(message = "Orientation cannot be blank")
  private String orientation;

  public RunwayModel(String id, Float length, Float width, RunwaySurfaceTypes surfaceType,
      Float maxWeightCapacity, String orientation) {
    this.id = id;
    this.length = length;
    this.width = width;
    this.surfaceType = surfaceType;
    this.maxWeightCapacity = maxWeightCapacity;
    this.orientation = orientation;
  }

  // Getter and Setters
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Float getLength() {
    return this.length;
  }

  public void setLength(Float length) {
    this.length = length;
  }

  public Float getWidth() {
    return this.width;
  }

  public void setWidth(Float width) {
    this.width = width;
  }

  public RunwaySurfaceTypes getSurfaceType() {
    return this.surfaceType;
  }

  public void getSurfaceType(RunwaySurfaceTypes surfaceType) {
    this.surfaceType = surfaceType;
  }

  public Float getMaxWeightCapacity() {
    return this.maxWeightCapacity;
  }

  public void setMaxWeightCapacity(Float maxWeightCapacity) {
    this.maxWeightCapacity = maxWeightCapacity;
  }

  public String getOrientation() {
    return this.orientation;
  }

  public void setOrientation(String orientation) {
    this.orientation = orientation;
  }
}
