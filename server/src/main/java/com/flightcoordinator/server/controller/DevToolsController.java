package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.DevToolsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${server.api-version}/devTools")
@Tag(name = "Development Tools' Controller", description = "Endpoint for various tools for development operations.")
public class DevToolsController {
  @Autowired
  private DevToolsService developmentService;

  @PostMapping("/createSampleData")
  @Operation(summary = "Create sample data for development", description = "Create sample data for development.")
  public ResponseEntity<ResponseObject<List<Object>>> createSampleData() {
    developmentService.createSampleData();
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }
}
