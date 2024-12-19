package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.AirportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/airport")
@Tag(name = "Airports Controller", description = "Endpoints for managing airports.")
public class AirportController {
  @Autowired
  private AirportService airportService;

  @PostMapping("/getAll")
  @PreAuthorize("hasAuthority('AIRPORT_READ')")
  @Operation(summary = "Get all the airports", description = "Retrieve the details of all airports.")
  public ResponseEntity<ResponseObject<List<AirportEntity>>> getAllAirports() {
    List<AirportEntity> airports = airportService.getAllAirports();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), airports);
  }

  @PostMapping("/getById")
  @PreAuthorize("hasAuthority('AIRPORT_READ')")
  @Operation(summary = "Get an airport by id", description = "Retrieve the details of a spesific airpot using it's ID.")
  public ResponseEntity<ResponseObject<AirportEntity>> getAirportById(@RequestBody String airportId) {
    AirportEntity airport = airportService.getSingleAirportById(airportId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), airport);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('AIRPORT_CREATE')")
  @Operation(summary = "Create a new airport", description = "Create a new airport.")
  public ResponseEntity<ResponseObject<Object>> createAirport(@RequestBody AirportEntity newAirport) {
    airportService.createAirport(newAirport);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  };

  @PatchMapping("/update")
  @PreAuthorize("hasAuthority('AIRPORT_UPDATE')")
  @Operation(summary = "Update an airport", description = "Update an existing airport.")
  public ResponseEntity<ResponseObject<Object>> updateAirport(@RequestBody String airportId,
      @RequestBody AirportEntity updatedAirport) {
    airportService.updateAirport(airportId, updatedAirport);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("/delete")
  @PreAuthorize("hasAuthority('AIRPORT_DELETE')")
  @Operation(summary = "Delete an airport", description = "Delete an existing airport.")
  public ResponseEntity<ResponseObject<Object>> deleteAirport(@RequestBody String airportId) {
    airportService.deleteAirport(airportId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
