package com.flightcoordinator.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.repository.AlgorithmResultRepository;

@Service
public class AlgorithmResultService {
  @Autowired
  private AlgorithmResultRepository algorithmResultRepository;
}
