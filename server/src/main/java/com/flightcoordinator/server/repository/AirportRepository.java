package com.flightcoordinator.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.AirportEntity;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, String> {
}
