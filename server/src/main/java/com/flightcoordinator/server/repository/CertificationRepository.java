package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.model.CertificationModel;

@Repository
public interface CertificationRepository extends MongoRepository<CertificationModel, String> {
}
