package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.model.VehicleModel;
import com.flightcoordinator.server.repository.VehicleRepository;

@Service
public class VehicleService {
  @Autowired
  private VehicleRepository repository;

  public VehicleModel getSingleVehicleById(String vehicleId) {
    Optional<VehicleModel> vehicle = repository.findById(vehicleId);
    return vehicle
        .orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<VehicleModel> getMultipleVehicleById(List<String> vehicleIds) {
    List<VehicleModel> vehicles = repository.findAllById(vehicleIds);
    if (vehicles.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return vehicles;
  }

  public List<VehicleModel> getAllVehicles() {
    List<VehicleModel> vehicles = repository.findAll();
    if (vehicles.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return vehicles;
  }

  public void createVehicle(VehicleModel newVehicle) {
    repository.save(newVehicle);
  }

  public void updateVehicle(String vehicleId, VehicleModel updatedVehicle) {
    VehicleModel existingVehicle = getSingleVehicleById(vehicleId);

    existingVehicle.setType(updatedVehicle.getType());
    existingVehicle.setVehicleCode(updatedVehicle.getVehicleCode());
    existingVehicle.setCapacity(updatedVehicle.getCapacity());
    existingVehicle.setAvailability(updatedVehicle.getAvailability());
    existingVehicle.setMaintenanceDue(updatedVehicle.getMaintenanceDue());

    repository.save(existingVehicle);
  }

  public void deleteVehicle(String vehicleId) {
    VehicleModel existingVehicle = getSingleVehicleById(vehicleId);
    repository.delete(existingVehicle);
  }

  public Boolean doesSingleVehicleExist(String vehicleId) {
    Optional<VehicleModel> vehicle = repository.findById(vehicleId);
    return vehicle.isPresent();
  }

  public Boolean doesMultipleVehicleExist(List<String> vehicleIds) {
    List<VehicleModel> vehicles = repository.findAllById(vehicleIds);
    if (vehicles.size() != vehicleIds.size()) {
      return false;
    }
    return vehicles.isEmpty();
  }
}
