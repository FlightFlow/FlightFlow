package com.flightcoordinator.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.RouteEntity;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, String> {
}
