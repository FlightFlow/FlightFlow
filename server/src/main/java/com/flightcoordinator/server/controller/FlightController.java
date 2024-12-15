package com.flightcoordinator.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.service.FlightService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/flight")
@Tag(name = "Flight Controller", description = "Endpoints for managing details of flights.")
public class FlightController {
  @Autowired
  private FlightService flightService;
}
