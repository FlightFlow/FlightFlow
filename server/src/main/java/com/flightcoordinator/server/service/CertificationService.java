package com.flightcoordinator.server.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.flightcoordinator.server.repository.CertificationRepository;

public class CertificationService {
  @Autowired
  private CertificationRepository repository;
}
