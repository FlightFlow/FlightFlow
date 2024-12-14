package com.flightcoordinator.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.service.AlgorithmResultService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/algorithm/result")
@Tag(name = "Algorithm Result Controller", description = "Endpoints for managing algorithm results.")
public class AlgorithmResultController {
  @Autowired
  private AlgorithmResultService algorithmResultService;
}
