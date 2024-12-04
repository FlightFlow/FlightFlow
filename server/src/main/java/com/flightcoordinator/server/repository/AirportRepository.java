package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightcoordinator.server.model.AirportModel;

public interface AirportRepository extends MongoRepository<AirportModel, String> {

}
