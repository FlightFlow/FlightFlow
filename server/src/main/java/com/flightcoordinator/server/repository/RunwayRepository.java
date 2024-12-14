package com.flightcoordinator.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.RunwayEntity;

@Repository
public interface RunwayRepository extends JpaRepository<RunwayEntity, String> {
}
