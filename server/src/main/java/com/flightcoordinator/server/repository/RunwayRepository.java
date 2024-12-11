package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.RunwayEntity;

@Repository
public interface RunwayRepository extends MongoRepository<RunwayEntity, String> {
}
