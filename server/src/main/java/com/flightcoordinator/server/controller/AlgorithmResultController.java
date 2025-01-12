package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.dto.AlgorithmResultDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.AlgorithmResultService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${server.api-version}/algorithm/result")
@Tag(name = "Algorithm Result Controller", description = "Endpoints for managing algorithm results.")
public class AlgorithmResultController {
  @Autowired
  private AlgorithmResultService algorithmResultService;

  @PostMapping("/getAll")
  @Operation(summary = "Get all the algorithm results", description = "Retrieve the details of all a spesific algorithm results.")
  public ResponseEntity<ResponseObject<List<AlgorithmResultDTO>>> getAllAlgorithms() {
    List<AlgorithmResultDTO> algorithmResults = algorithmResultService.getAllAlgorithmResults();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", algorithmResults);
  }

  @PostMapping("/getById")
  @Operation(summary = "Get an algorithm result by id", description = "Retrieve the details of a spesific algorithm result using it's ID.")
  public ResponseEntity<ResponseObject<AlgorithmResultDTO>> getAlgorithmResultById(@RequestBody EntityIdDTO id) {
    AlgorithmResultDTO algorithmResult = algorithmResultService.getSingleAlgorithmResultById(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", algorithmResult);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new algorithm result", description = "Create a new algorithm result. (Not intended for manual use)")
  public ResponseEntity<ResponseObject<Object>> createAlgorithmResult(
      @RequestBody AlgorithmResultDTO newAlgorithmResult) {
    algorithmResultService.createAlgorithmResult(newAlgorithmResult);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @DeleteMapping("/delete")
  @Operation(summary = "Delete an algorithm result", description = "Delete an algorithm result.")
  public ResponseEntity<ResponseObject<Object>> deleteAlgorithmResult(@RequestBody EntityIdDTO id) {
    algorithmResultService.deleteAlgorithmResult(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
