package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.entity.PlaneEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.PlaneService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/plane")
@Tag(name = "Plane Controller", description = "Endpoints for managing planes.")
public class PlaneController {
  @Autowired
  private PlaneService planeService;

  @GetMapping("/getById/{planeId}")
  @PreAuthorize("hasAuthority('PLANE_READ')")
  @Operation(summary = "Get a plane by id", description = "Retrieve the details of a spesific plane using it's ID.")
  public ResponseEntity<ResponseObject<PlaneEntity>> getPlaneById(@PathVariable String planeId) {
    PlaneEntity plane = planeService.getSinglePlaneById(planeId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), plane);
  }

  @GetMapping("/getAll")
  @PreAuthorize("hasAuthority('FLIGHT_READ')")
  @Operation(summary = "Get all the planes", description = "Retrieve the details of all planes.")
  public ResponseEntity<ResponseObject<List<PlaneEntity>>> getAllPlanes() {
    List<PlaneEntity> planes = planeService.getAllPlanes();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), planes);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('FLIGHT_CREATE')")
  @Operation(summary = "Create a new plane", description = "Create a new plane.")
  public ResponseEntity<ResponseObject<Object>> createPlane(@RequestBody PlaneEntity newPlane) {
    planeService.createPlane(newPlane);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PatchMapping("/update/{planeId}")
  @PreAuthorize("hasAuthority('FLIGHT_UPDATE')")
  @Operation(summary = "Update an plane", description = "Update an existing plane.")
  public ResponseEntity<ResponseObject<Object>> updatePlane(@PathVariable String planeId,
      @RequestBody PlaneEntity updatedPlane) {
    planeService.updatePlane(planeId, updatedPlane);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("/delete/{planeId}")
  @PreAuthorize("hasAuthority('FLIGHT_DELETE')")
  @Operation(summary = "Delete an plane", description = "Delete an existing plane.")
  public ResponseEntity<ResponseObject<Object>> deletePlane(@PathVariable String planeId) {
    planeService.deletePlane(planeId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
