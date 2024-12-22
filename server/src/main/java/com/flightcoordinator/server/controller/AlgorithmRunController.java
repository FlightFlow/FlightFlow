package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.entity.AlgorithmRunEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.AlgorithmRunService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${server.api-version}/algorithm/run")
@Tag(name = "Algorithm Run Controller", description = "Endpoints for managing details of algorithm runs.")
public class AlgorithmRunController {
  @Autowired
  private AlgorithmRunService algorithmRunService;

  @PostMapping("/getAll")
  @PreAuthorize("hasAuthority('ALGO_RUN_READ')")
  @Operation(summary = "Get all the algorithm runs", description = "Retrieve the details of all a spesific algorithm runs.")
  public ResponseEntity<ResponseObject<List<AlgorithmRunEntity>>> getAllAlgorithms() {
    List<AlgorithmRunEntity> algorithmRuns = algorithmRunService.getAllAlgorithmRuns();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", algorithmRuns);
  }

  @PostMapping("/getById")
  @PreAuthorize("hasAuthority('ALGO_RUN_READ')")
  @Operation(summary = "Get an algorithm run by id", description = "Retrieve the details of a spesific algorithm run using it's ID.")
  public ResponseEntity<ResponseObject<AlgorithmRunEntity>> getAlgorithmRunById(@RequestBody String algorithmRunId) {
    AlgorithmRunEntity algorithmRun = algorithmRunService.getSingleAlgorithmRunById(algorithmRunId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", algorithmRun);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('ALGO_RUN_CREATE')")
  @Operation(summary = "Create a new algorithm run", description = "Create a new algorithm run. (Not intended for manual use)")
  public ResponseEntity<ResponseObject<Object>> createAlgorithmRun(
      @RequestBody AlgorithmRunEntity newAlgorithmRun) {
    algorithmRunService.createAlgorithmRun(newAlgorithmRun);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @DeleteMapping("/delete")
  @PreAuthorize("hasAuthority('ALGO_RUN_DELETE')")
  @Operation(summary = "Delete an algorithm run", description = "Delete an algorithm run.")
  public ResponseEntity<ResponseObject<Object>> deleteAlgorithmRun(@RequestBody String algorithmRunId) {
    algorithmRunService.deleteAlgorithmRun(algorithmRunId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
