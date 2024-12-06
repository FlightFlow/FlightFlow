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

import com.flightcoordinator.server.model.AirportModel;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.AirportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/airport")
@Tag(name = "Airports Controller", description = "Endpoints for managing airports.")
public class AirportController {
  @Autowired
  private AirportService service;

  @GetMapping("/getById/{airportId}")
  @Operation(summary = "Get a airport by id", description = "Retrieve the details of a spesific airpot using it's ID")
  public ResponseEntity<ResponseObject<AirportModel>> getAirportById(@PathVariable String airportId) {
    AirportModel airport = service.getSingleAirportById(airportId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), airport);
  }

  @GetMapping("/getAll")
  @Operation(summary = "Get all the airports", description = "Retrieve the details of all airports.")
  public ResponseEntity<ResponseObject<List<AirportModel>>> getAllAirports() {
    List<AirportModel> airports = service.getAllAirports();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), airports);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new airport", description = "Create a new airport.")
  public ResponseEntity<ResponseObject<Object>> createAirport(@RequestBody AirportModel newAirport) {
    service.createAirport(newAirport);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  };

  @PatchMapping("/update/{airportId}")
  @Operation(summary = "Update an airport", description = "Update an existing airport.")
  public ResponseEntity<ResponseObject<Object>> updateAirport(@PathVariable String airportId,
      @RequestBody AirportModel updatedAirport) {
    service.updateAirport(airportId, updatedAirport);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("/delete/{airportId}")
  @Operation(summary = "Delete an airport", description = "Delete an existing airport.")
  public ResponseEntity<ResponseObject<Object>> deleteAirport(@PathVariable String airportId) {
    service.deleteAirport(airportId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
