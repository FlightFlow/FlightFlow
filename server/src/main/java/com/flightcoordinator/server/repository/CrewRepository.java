package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.CrewEntity;

@Repository
public interface CrewRepository extends MongoRepository<CrewEntity, String> {
}
