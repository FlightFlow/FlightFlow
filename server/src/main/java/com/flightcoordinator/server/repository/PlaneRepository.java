package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.model.PlaneModel;

@Repository
public interface PlaneRepository extends MongoRepository<PlaneModel, String> {
}
