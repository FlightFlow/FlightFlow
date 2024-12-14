package com.flightcoordinator.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.AlgorithmRunEntity;

@Repository
public interface AlgorithmRunRepository extends JpaRepository<AlgorithmRunEntity, String> {
}
