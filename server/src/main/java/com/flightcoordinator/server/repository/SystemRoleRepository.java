package com.flightcoordinator.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightcoordinator.server.entity.SystemRoleEntity;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRoleEntity, String> {
}
