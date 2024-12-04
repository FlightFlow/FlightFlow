package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightcoordinator.server.model.RouteModel;

public interface RouteRepository extends MongoRepository<RouteModel, String> {

}
