package com.flightcoordinator.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.CrewEntity;

@Repository
public interface CrewRepository extends JpaRepository<CrewEntity, String> {
}
