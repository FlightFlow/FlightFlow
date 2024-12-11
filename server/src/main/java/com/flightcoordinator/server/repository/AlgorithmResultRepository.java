package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.AlgorithmResultEntity;

@Repository
public interface AlgorithmResultRepository extends MongoRepository<AlgorithmResultEntity, String> {
}
