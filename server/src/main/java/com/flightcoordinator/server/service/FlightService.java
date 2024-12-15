package com.flightcoordinator.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.repository.FlightRepository;

@Service
public class FlightService {
  @Autowired
  private FlightRepository flightRepository;
}
