package com.flightcoordinator.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.repository.AlgorithmRunRepository;

@Service
public class AlgorithmRunService {
  @Autowired
  private AlgorithmRunRepository repository;
}
