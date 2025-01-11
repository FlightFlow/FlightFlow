package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.VehicleDTO;
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
  @Operation(summary = "Get all the vehicles", description = "Retrieve the details of all vehicles.")
  public ResponseEntity<ResponseObject<List<VehicleDTO>>> getAllVehicles() {
    List<VehicleDTO> vehicles = vehicleService.getAllVehicles();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", vehicles);
  }

  @PostMapping("/getById")
  @Operation(summary = "Get a vehicle by id", description = "Retrieve the details of a spesific vehicle using it's ID.")
  public ResponseEntity<ResponseObject<VehicleDTO>> getVehicleById(@RequestBody EntityIdDTO id) {
    VehicleDTO vehicle = vehicleService.getSingleVehicleById(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", vehicle);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new vehicle", description = "Create a new vehicle.")
  public ResponseEntity<ResponseObject<Object>> createVehicle(@RequestBody VehicleDTO newVehicle) {
    vehicleService.createVehicle(newVehicle);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @PatchMapping("/update")
  @Operation(summary = "Update an vehicle", description = "Update an existing vehicle.")
  public ResponseEntity<ResponseObject<Object>> updateVehicle(@RequestBody VehicleDTO updatedVehicle) {
    vehicleService.updateVehicle(updatedVehicle);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.updateResponse", null);
  }

  @DeleteMapping("/delete")
  @Operation(summary = "Delete an vehicle", description = "Delete an existing vehicle.")
  public ResponseEntity<ResponseObject<Object>> deleteVehicle(@RequestBody EntityIdDTO id) {
    vehicleService.deleteVehicle(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
