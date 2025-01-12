package com.flightcoordinator.server.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.dto.AlgorithmRunDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
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
  @Operation(summary = "Get all the algorithm runs", description = "Retrieve the details of all a spesific algorithm runs.")
  public ResponseEntity<ResponseObject<List<AlgorithmRunDTO>>> getAllAlgorithms() {
    List<AlgorithmRunDTO> algorithmRuns = algorithmRunService.getAllAlgorithmRuns();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", algorithmRuns);
  }

  @PostMapping("/getById")
  @Operation(summary = "Get an algorithm run by id", description = "Retrieve the details of a spesific algorithm run using it's ID.")
  public ResponseEntity<ResponseObject<AlgorithmRunDTO>> getAlgorithmRunById(@RequestBody EntityIdDTO id) {
    AlgorithmRunDTO algorithmRun = algorithmRunService.getSingleAlgorithmRunById(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", algorithmRun);
  }

  @PostMapping("/trigger")
  @Operation(summary = "Create a new algorithm run", description = "Create a new algorithm run. (Not intended for manual use)")
  public ResponseEntity<ResponseObject<Object>> triggerAlgorithmRun(@RequestBody Map<String, String> algorithmName) {
    algorithmRunService.triggerAlgorithmRun(algorithmName.get("algorithmName"));
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new algorithm run", description = "Create a new algorithm run. (Not intended for manual use)")
  public ResponseEntity<ResponseObject<Object>> createAlgorithmRun(
      @RequestBody AlgorithmRunDTO newAlgorithmRun) {
    algorithmRunService.createAlgorithmRun(newAlgorithmRun);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @DeleteMapping("/delete")
  @Operation(summary = "Delete an algorithm run", description = "Delete an algorithm run.")
  public ResponseEntity<ResponseObject<Object>> deleteAlgorithmRun(@RequestBody EntityIdDTO id) {
    algorithmRunService.deleteAlgorithmRun(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
