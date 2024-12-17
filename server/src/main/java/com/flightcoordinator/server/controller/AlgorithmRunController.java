package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/${api.version}/algorithm/run")
@Tag(name = "Algorithm Run Controller", description = "Endpoints for managing details of algorithm runs.")
public class AlgorithmRunController {
  @Autowired
  private AlgorithmRunService algorithmRunService;

  @GetMapping("/getById/{algorithmRunId}")
  @PreAuthorize("hasAuthority('ALGO_RUN_READ')")
  @Operation(summary = "Get an algorithm run by id", description = "Retrieve the details of a spesific algorithm run using it's ID.")
  public ResponseEntity<ResponseObject<AlgorithmRunEntity>> getAlgorithmRunById(@PathVariable String algorithmRunId) {
    AlgorithmRunEntity algorithmRun = algorithmRunService.getSingleAlgorithmRunById(algorithmRunId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), algorithmRun);
  }

  @GetMapping("/getAll")
  @PreAuthorize("hasAuthority('ALGO_RUN_READ')")
  @Operation(summary = "Get all the algorithm runs", description = "Retrieve the details of all a spesific algorithm runs.")
  public ResponseEntity<ResponseObject<List<AlgorithmRunEntity>>> getAllAlgorithms() {
    List<AlgorithmRunEntity> algorithmRuns = algorithmRunService.getAllAlgorithmRuns();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), algorithmRuns);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('ALGO_RUN_CREATE')")
  @Operation(summary = "Create a new algorithm run", description = "Create a new algorithm run. (Not intended for manual use)")
  public ResponseEntity<ResponseObject<Object>> createAlgorithmRun(
      @RequestBody AlgorithmRunEntity newAlgorithmRun) {
    algorithmRunService.createAlgorithmRun(newAlgorithmRun);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PostMapping("/delete/{algorithmRunId}")
  @PreAuthorize("hasAuthority('ALGO_RUN_DELETE')")
  @Operation(summary = "Delete an algorithm run", description = "Delete an algorithm run.")
  public ResponseEntity<ResponseObject<Object>> deleteAlgorithmRun(@PathVariable String algorithmRunId) {
    algorithmRunService.deleteAlgorithmRun(algorithmRunId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
