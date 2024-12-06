package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.model.AirportModel;

@Repository
public interface AirportRepository extends MongoRepository<AirportModel, String> {
}
