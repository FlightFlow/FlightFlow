package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.entity.VehicleEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${server.api-version}/vehicle")
@Tag(name = "Vehicle Controller", description = "Endpoints for managing vehicles.")
public class VehicleController {
  @Autowired
  private VehicleService vehicleService;

  @PostMapping("/getAll")
  @PreAuthorize("hasAuthority('VEHICLE_READ')")
  @Operation(summary = "Get all the vehicles", description = "Retrieve the details of all vehicles.")
  public ResponseEntity<ResponseObject<List<VehicleEntity>>> getAllVehicles() {
    List<VehicleEntity> vehicles = vehicleService.getAllVehicles();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", vehicles);
  }

  @PostMapping("/getById")
  @PreAuthorize("hasAuthority('VEHICLE_READ')")
  @Operation(summary = "Get a vehicle by id", description = "Retrieve the details of a spesific vehicle using it's ID.")
  public ResponseEntity<ResponseObject<VehicleEntity>> getVehicleById(@RequestBody String vehicleId) {
    VehicleEntity vehicle = vehicleService.getSingleVehicleById(vehicleId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", vehicle);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('VEHICLE_CREATE')")
  @Operation(summary = "Create a new vehicle", description = "Create a new vehicle.")
  public ResponseEntity<ResponseObject<Object>> createVehicle(@RequestBody VehicleEntity newVehicle) {
    vehicleService.createVehicle(newVehicle);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @PatchMapping("/update")
  @PreAuthorize("hasAuthority('VEHICLE_UPDATE')")
  @Operation(summary = "Update an vehicle", description = "Update an existing vehicle.")
  public ResponseEntity<ResponseObject<Object>> updateVehicle(@RequestBody String vehicleId,
      @RequestBody VehicleEntity updatedVehicle) {
    vehicleService.updateVehicle(vehicleId, updatedVehicle);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.updateResponse", null);
  }

  @DeleteMapping("/delete")
  @PreAuthorize("hasAuthority('VEHICLE_DELETE')")
  @Operation(summary = "Delete an vehicle", description = "Delete an existing vehicle.")
  public ResponseEntity<ResponseObject<Object>> deleteVehicle(@RequestBody String vehicleId) {
    vehicleService.deleteVehicle(vehicleId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
