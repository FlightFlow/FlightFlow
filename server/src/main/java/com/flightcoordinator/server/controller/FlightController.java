package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.dto.FlightDTO;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.FlightService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${server.api-version}/flight")
@Tag(name = "Flight Controller", description = "Endpoints for managing details of flights.")
public class FlightController {
  @Autowired
  private FlightService flightService;

  @PostMapping("/getAll")
  @Operation(summary = "Get all the flights", description = "Retrieve the details of all flights.")
  public ResponseEntity<ResponseObject<List<FlightDTO>>> getAllFlights() {
    List<FlightDTO> flights = flightService.getAllFlights();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", flights);
  }

  @PostMapping("/getById")
  @Operation(summary = "Get a flight by id", description = "Retrieve the details of a spesific flight using it's ID.")
  public ResponseEntity<ResponseObject<FlightDTO>> getFlightById(@RequestBody String flightId) {
    FlightDTO flight = flightService.getSingleFlightById(flightId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", flight);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new flight", description = "Create a new flight.")
  public ResponseEntity<ResponseObject<Object>> createFlight(@RequestBody FlightDTO flight) {
    flightService.createFlight(flight);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @PatchMapping("/update")
  @Operation(summary = "Update a flight", description = "Update an existing flight.")
  public ResponseEntity<ResponseObject<Object>> updateFlight(@RequestBody String flightId,
      @RequestBody FlightDTO flight) {
    flightService.updateFlight(flightId, flight);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.updateResponse", null);
  }

  @DeleteMapping("/delete")
  @Operation(summary = "Delete a flight", description = "Delete an existing flight.")
  public ResponseEntity<ResponseObject<Object>> deleteFlight(@RequestBody String flightId) {
    flightService.deleteFlight(flightId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
