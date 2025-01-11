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

import com.flightcoordinator.server.dto.AirportDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.create_update.AirportCreateUpdateDTO;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.AirportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${server.api-version}/airport")
@Tag(name = "Airports Controller", description = "Endpoints for managing airports.")
public class AirportController {
  @Autowired
  private AirportService airportService;

  @PostMapping("/getAll")
  @Operation(summary = "Get all the airports", description = "Retrieve the details of all airports.")
  public ResponseEntity<ResponseObject<List<AirportDTO>>> getAllAirports() {
    List<AirportDTO> airports = airportService.getAllAirports();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", airports);
  }

  @PostMapping("/getById")
  @Operation(summary = "Get an airport by id", description = "Retrieve the details of a spesific airpot using it's ID.")
  public ResponseEntity<ResponseObject<AirportDTO>> getAirportById(@RequestBody EntityIdDTO id) {
    AirportDTO airport = airportService.getSingleAirportById(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "", airport);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new airport", description = "Create a new airport.")
  public ResponseEntity<ResponseObject<Object>> createAirport(@RequestBody AirportCreateUpdateDTO newAirport) {
    airportService.createAirport(newAirport);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse",
        null);
  };

  @PatchMapping("/update")
  @Operation(summary = "Update an airport", description = "Update an existing airport.")
  public ResponseEntity<ResponseObject<Object>> updateAirport(@RequestBody AirportCreateUpdateDTO updatedAirport) {
    airportService.updateAirport(updatedAirport);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.updateResponse", null);
  }

  @DeleteMapping("/delete")
  @Operation(summary = "Delete an airport", description = "Delete an existing airport.")
  public ResponseEntity<ResponseObject<Object>> deleteAirport(@RequestBody EntityIdDTO id) {
    airportService.deleteAirport(id);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
