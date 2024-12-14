package com.flightcoordinator.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.service.AlgorithmRunService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/algorithm/run")
@Tag(name = "Algorithm Run Controller", description = "Endpoints for managing details of algorithm runs.")
public class AlgorithmRunController {
  @Autowired
  private AlgorithmRunService algorithmRunService;
}
