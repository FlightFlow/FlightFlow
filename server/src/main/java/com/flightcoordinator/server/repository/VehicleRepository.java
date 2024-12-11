package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.VehicleEntity;

@Repository
public interface VehicleRepository extends MongoRepository<VehicleEntity, String> {
}
