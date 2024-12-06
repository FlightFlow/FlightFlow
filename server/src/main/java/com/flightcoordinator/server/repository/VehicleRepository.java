package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.model.VehicleModel;

@Repository
public interface VehicleRepository extends MongoRepository<VehicleModel, String> {
}
