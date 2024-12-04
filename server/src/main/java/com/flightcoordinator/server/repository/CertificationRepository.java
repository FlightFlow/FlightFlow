package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightcoordinator.server.model.CertificationModel;

public interface CertificationRepository extends MongoRepository<CertificationModel, String> {
}
