package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightcoordinator.server.model.RunwayModel;

public interface RunwayRepository extends MongoRepository<RunwayModel, String> {

}
