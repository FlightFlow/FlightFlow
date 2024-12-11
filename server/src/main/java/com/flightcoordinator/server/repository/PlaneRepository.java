package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.PlaneEntity;

@Repository
public interface PlaneRepository extends MongoRepository<PlaneEntity, String> {
}
