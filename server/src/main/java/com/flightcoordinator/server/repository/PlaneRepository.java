package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightcoordinator.server.model.PlaneModel;

public interface PlaneRepository extends MongoRepository<PlaneModel, String> {

}
