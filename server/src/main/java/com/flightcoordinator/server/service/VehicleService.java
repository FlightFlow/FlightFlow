package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.VehicleEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.VehicleRepository;

@Service
public class VehicleService {
  @Autowired
  private VehicleRepository vehicleRepository;

  public VehicleEntity getSingleVehicleById(String vehicleId) {
    Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
    return vehicle.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<VehicleEntity> getMultipleVehicleById(List<String> vehicleIds) {
    List<VehicleEntity> vehicles = vehicleRepository.findAllById(vehicleIds);
    if (vehicles.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return vehicles;
  }

  public List<VehicleEntity> getAllVehicles() {
    List<VehicleEntity> vehicles = vehicleRepository.findAll();
    if (vehicles.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return vehicles;
  }

  public void createVehicle(VehicleEntity newVehicle) {
    vehicleRepository.save(newVehicle);
  }

  public void updateVehicle(String vehicleId, VehicleEntity updatedVehicle) {
    VehicleEntity existingVehicle = getSingleVehicleById(vehicleId);

    existingVehicle.setType(updatedVehicle.getType());
    existingVehicle.setVehicleCode(updatedVehicle.getVehicleCode());
    existingVehicle.setCapacity(updatedVehicle.getCapacity());
    existingVehicle.setAvailability(updatedVehicle.getAvailability());
    existingVehicle.setMaintenanceDue(updatedVehicle.getMaintenanceDue());

    vehicleRepository.save(existingVehicle);
  }

  public void deleteVehicle(String vehicleId) {
    VehicleEntity existingVehicle = getSingleVehicleById(vehicleId);
    vehicleRepository.delete(existingVehicle);
  }
}
