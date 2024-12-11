package com.flightcoordinator.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.RouteEntity;

@Repository
public interface RouteRepository extends MongoRepository<RouteEntity, String> {
}
