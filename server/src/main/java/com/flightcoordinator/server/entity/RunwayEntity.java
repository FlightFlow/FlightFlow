package com.flightcoordinator.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightcoordinator.server.enums.RunwaySurfaceTypes;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "runway")
public class RunwayEntity {
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

  public RunwayEntity() {
  }

  public RunwayEntity(String id, @NotBlank(message = "Length cannot be blank") Float length,
      @NotBlank(message = "Width cannot be blank") Float width,
      @NotBlank(message = "Surface type cannot be blank") RunwaySurfaceTypes surfaceType,
      @NotBlank(message = "Max weigth capacity cannot be blank") @Min(value = 1, message = "Max weight capacity should be greater than '1'") Float maxWeightCapacity,
      @NotBlank(message = "Orientation cannot be blank") String orientation) {
    this.id = id;
    this.length = length;
    this.width = width;
    this.surfaceType = surfaceType;
    this.maxWeightCapacity = maxWeightCapacity;
    this.orientation = orientation;
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

  public RunwaySurfaceTypes getSurfaceType() {
    return surfaceType;
  }

  public void setSurfaceType(RunwaySurfaceTypes surfaceType) {
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
}
