package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.AlgorithmRunEntity;

@Repository
public interface AlgorithmRunRepository extends MongoRepository<AlgorithmRunEntity, String> {
}
