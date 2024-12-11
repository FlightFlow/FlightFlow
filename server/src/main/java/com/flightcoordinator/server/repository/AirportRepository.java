package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.AirportEntity;

@Repository
public interface AirportRepository extends MongoRepository<AirportEntity, String> {
}
