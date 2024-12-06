package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.model.VehicleModel;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/vehicle")
@Tag(name = "Vehicle Controller", description = "Endpoints for managing vehicles.")
public class VehicleController {
  @Autowired
  private VehicleService service;

  @GetMapping("/getById/{vehicleId}")
  @Operation(summary = "Get a vehicle by id", description = "Retrieve the details of a spesific vehicle using it's ID")
  public ResponseEntity<ResponseObject<VehicleModel>> getVehicleById(@PathVariable String vehicleId) {
    VehicleModel vehicle = service.getSingleVehicleById(vehicleId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), vehicle);
  }

  @GetMapping("/getAll")
  @Operation(summary = "Get all the vehicles", description = "Retrieve the details of all vehicles.")
  public ResponseEntity<ResponseObject<List<VehicleModel>>> getAllVehicles() {
    List<VehicleModel> vehicles = service.getAllVehicles();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), vehicles);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new vehicle", description = "Create a new vehicle.")
  public ResponseEntity<ResponseObject<Object>> createVehicle(@RequestBody VehicleModel newVehicle) {
    service.createVehicle(newVehicle);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PatchMapping("/update/{vehicleId}")
  @Operation(summary = "Update an vehicle", description = "Update an existing vehicle.")
  public ResponseEntity<ResponseObject<Object>> updateVehicle(@PathVariable String vehicleId,
      @RequestBody VehicleModel updatedVehicle) {
    service.updateVehicle(vehicleId, updatedVehicle);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("/delete/{vehicleId}")
  @Operation(summary = "Delete an vehicle", description = "Delete an existing vehicle.")
  public ResponseEntity<ResponseObject<Object>> deleteVehicle(@PathVariable String vehicleId) {
    service.deleteVehicle(vehicleId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
