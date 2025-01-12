package com.flightcoordinator.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.devtools.SampleDataGenerator;

@Service
public class DevToolsService {
  @Autowired
  private SampleDataGenerator sampleDataService;

  public void createSampleData() {
    sampleDataService
        .generateSampleAirports()
        .generateSampleCrewMembers()
        .generateSampleCertifications()
        .generateSampleRoutes()
        .generateSampleFlights()
        .generateSamplePlanes()
        .generateSampleRunways()
        .generateSampleVehicles();
  }
}
