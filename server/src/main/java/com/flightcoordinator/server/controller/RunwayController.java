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

import com.flightcoordinator.server.entity.RunwayEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.RunwayService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/runway")
@Tag(name = "Runway Controller", description = "Endpoints for managing airports' runways.")
public class RunwayController {
  @Autowired
  private RunwayService runwayService;

  @GetMapping("/getById/{runwayId}")
  @Operation(summary = "Get a runway by id", description = "Retrieve the details of a spesific runway using it's ID.")
  public ResponseEntity<ResponseObject<RunwayEntity>> getRunwayById(@PathVariable String runwayId) {
    RunwayEntity runway = runwayService.getSingleRunwayById(runwayId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), runway);
  }

  @GetMapping("/getAll")
  @Operation(summary = "Get all the runways", description = "Retrieve the details of all runways.")
  public ResponseEntity<ResponseObject<List<RunwayEntity>>> getAllRunways() {
    List<RunwayEntity> runways = runwayService.getAllRunways();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), runways);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new runway", description = "Create a new runway.")
  public ResponseEntity<ResponseObject<Object>> createRunway(@RequestBody RunwayEntity runway) {
    runwayService.createRunway(runway);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PatchMapping("/update/{runwayId}")
  @Operation(summary = "Update a runway", description = "Update an existing runway.")
  public ResponseEntity<ResponseObject<Object>> updateRunway(@PathVariable String runwayId,
      @RequestBody RunwayEntity runway) {
    runwayService.updateRunway(runwayId, runway);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("/delete/{runwayId}")
  @Operation(summary = "Delete a runway", description = "Delete an existing runway.")
  public ResponseEntity<ResponseObject<Object>> deleteRunway(@PathVariable String runwayId) {
    runwayService.deleteRunway(runwayId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
