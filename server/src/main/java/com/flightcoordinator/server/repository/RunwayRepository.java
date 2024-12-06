package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.model.RunwayModel;

@Repository
public interface RunwayRepository extends MongoRepository<RunwayModel, String> {
}
