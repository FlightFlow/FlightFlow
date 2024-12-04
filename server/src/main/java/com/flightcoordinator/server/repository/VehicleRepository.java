package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.flightcoordinator.server.model.VehicleModel;

public interface VehicleRepository extends MongoRepository<VehicleModel, String> {

}
