package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.CertificationEntity;

@Repository
public interface CertificationRepository extends MongoRepository<CertificationEntity, String> {
}
