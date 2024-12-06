package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.model.RouteModel;

@Repository
public interface RouteRepository extends MongoRepository<RouteModel, String> {
}
