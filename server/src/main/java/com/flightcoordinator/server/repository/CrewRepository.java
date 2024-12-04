package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightcoordinator.server.model.CrewModel;

public interface CrewRepository extends MongoRepository<CrewModel, String> {

}
