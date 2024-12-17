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

import com.flightcoordinator.server.entity.AlgorithmResultEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.AlgorithmResultService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/algorithm/result")
@Tag(name = "Algorithm Result Controller", description = "Endpoints for managing algorithm results.")
public class AlgorithmResultController {
  @Autowired
  private AlgorithmResultService algorithmResultService;

  @GetMapping("/getById/{algorithmResultId}")
  @PreAuthorize("hasAuthority('ALGO_RESULT_READ')")
  @Operation(summary = "Get an algorithm result by id", description = "Retrieve the details of a spesific algorithm result using it's ID.")
  public ResponseEntity<ResponseObject<AlgorithmResultEntity>> getAlgorithmResultById(
      @PathVariable String algorithmResultId) {
    AlgorithmResultEntity algorithmResult = algorithmResultService.getSingleAlgorithmResultById(algorithmResultId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(),
        algorithmResult);
  }

  @GetMapping("/getAll")
  @PreAuthorize("hasAuthority('ALGO_RESULT_READ')")
  @Operation(summary = "Get all the algorithm results", description = "Retrieve the details of all a spesific algorithm results.")
  public ResponseEntity<ResponseObject<List<AlgorithmResultEntity>>> getAllAlgorithms() {
    List<AlgorithmResultEntity> algorithmResults = algorithmResultService.getAllAlgorithmResults();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(),
        algorithmResults);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('ALGO_RESULT_CREATE')")
  @Operation(summary = "Create a new algorithm result", description = "Create a new algorithm result. (Not intended for manual use)")
  public ResponseEntity<ResponseObject<Object>> createAlgorithmResult(
      @RequestBody AlgorithmResultEntity newAlgorithmResult) {
    algorithmResultService.createAlgorithmResult(newAlgorithmResult);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PostMapping("/delete/{algorithmResultId}")
  @PreAuthorize("hasAuthority('ALGO_RESULT_DELETE')")
  @Operation(summary = "Delete an algorithm result", description = "Delete an algorithm result.")
  public ResponseEntity<ResponseObject<Object>> deleteAlgorithmResult(@PathVariable String algorithmResultId) {
    algorithmResultService.deleteAlgorithmResult(algorithmResultId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
