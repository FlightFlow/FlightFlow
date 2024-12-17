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

import com.flightcoordinator.server.entity.FlightEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.FlightService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/flight")
@Tag(name = "Flight Controller", description = "Endpoints for managing details of flights.")
public class FlightController {
  @Autowired
  private FlightService flightService;

  @GetMapping("/getById/{flightId}")
  @PreAuthorize("hasAuthority('FLIGHT_READ')")
  @Operation(summary = "Get a flight by id", description = "Retrieve the details of a spesific flight using it's ID.")
  public ResponseEntity<ResponseObject<FlightEntity>> getFlightById(@PathVariable String flightId) {
    FlightEntity flight = flightService.getSingleFlightById(flightId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), flight);
  }

  @GetMapping("/getAll")
  @PreAuthorize("hasAuthority('FLIGHT_READ')")
  @Operation(summary = "Get all the flights", description = "Retrieve the details of all flights.")
  public ResponseEntity<ResponseObject<List<FlightEntity>>> getAllFlights() {
    List<FlightEntity> flights = flightService.getAllFlights();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), flights);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('FLIGHT_CREATE')")
  @Operation(summary = "Create a new flight", description = "Create a new flight.")
  public ResponseEntity<ResponseObject<Object>> createFlight(@RequestBody FlightEntity flight) {
    flightService.createFlight(flight);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PatchMapping("/update/{flightId}")
  @PreAuthorize("hasAuthority('FLIGHT_UPDATE')")
  @Operation(summary = "Update a flight", description = "Update an existing flight.")
  public ResponseEntity<ResponseObject<Object>> updateFlight(@PathVariable String flightId,
      @RequestBody FlightEntity flight) {
    flightService.updateFlight(flightId, flight);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("/delete/{flightId}")
  @PreAuthorize("hasAuthority('FLIGHT_DELETE')")
  @Operation(summary = "Delete a flight", description = "Delete an existing flight.")
  public ResponseEntity<ResponseObject<Object>> deleteFlight(@PathVariable String flightId) {
    flightService.deleteFlight(flightId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
