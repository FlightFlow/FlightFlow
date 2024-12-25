package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.VehicleDTO;
import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.entity.VehicleEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.repository.VehicleRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class VehicleService {
  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private AirportRepository airportRepository;

  public VehicleDTO getSingleVehicleById(String vehicleId) {
    VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
        .orElseThrow(() -> new AppError("notFound.vehicle", HttpStatus.NOT_FOUND.value()));
    VehicleDTO vehicleDTO = ObjectMapper.toVehicleDTO(vehicle);
    return vehicleDTO;
  }

  public List<VehicleDTO> getMultipleVehicleById(List<String> vehicleIds) {
    List<VehicleEntity> vehicles = vehicleRepository.findAllById(vehicleIds);
    if (vehicles.isEmpty()) {
      throw new AppError("notFound.vehicle", HttpStatus.NOT_FOUND.value());
    }
    List<VehicleDTO> vehicleDTOs = vehicles.stream().map(ObjectMapper::toVehicleDTO).collect(Collectors.toList());
    return vehicleDTOs;
  }

  public List<VehicleDTO> getAllVehicles() {
    List<VehicleEntity> vehicles = vehicleRepository.findAll();
    if (vehicles.isEmpty()) {
      throw new AppError("notFound.vehicle", HttpStatus.NOT_FOUND.value());
    }
    List<VehicleDTO> vehicleDTOs = vehicles.stream().map(ObjectMapper::toVehicleDTO).collect(Collectors.toList());
    return vehicleDTOs;
  }

  public void createVehicle(VehicleDTO newVehicleDTO) {
    AirportEntity newAirportEntity = airportRepository.findById(newVehicleDTO.getAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    VehicleEntity newVehicleEntity = new VehicleEntity();
    newVehicleEntity.setType(newVehicleDTO.getType());
    newVehicleEntity.setVehicleCode(newVehicleDTO.getVehicleCode());
    newVehicleEntity.setCapacity(newVehicleDTO.getCapacity());
    newVehicleEntity.setAvailability(newVehicleDTO.getAvailability());
    newVehicleEntity.setMaintenanceDue(newVehicleDTO.getMaintenanceDue());
    newVehicleEntity.setAirport(newAirportEntity);

    vehicleRepository.save(newVehicleEntity);
  }

  public void updateVehicle(String vehicleId, VehicleDTO updatedVehicleDTO) {
    AirportEntity newAirportEntity = airportRepository.findById(updatedVehicleDTO.getAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    VehicleEntity existingVehicle = vehicleRepository.findById(vehicleId)
        .orElseThrow(() -> new AppError("notFound.vehicle", HttpStatus.NOT_FOUND.value()));

    existingVehicle.setType(updatedVehicleDTO.getType());
    existingVehicle.setVehicleCode(updatedVehicleDTO.getVehicleCode());
    existingVehicle.setCapacity(updatedVehicleDTO.getCapacity());
    existingVehicle.setAvailability(updatedVehicleDTO.getAvailability());
    existingVehicle.setMaintenanceDue(updatedVehicleDTO.getMaintenanceDue());
    existingVehicle.setAirport(newAirportEntity);

    vehicleRepository.save(existingVehicle);
  }

  public void deleteVehicle(String vehicleId) {
    VehicleEntity existingVehicle = vehicleRepository.findById(vehicleId)
        .orElseThrow(() -> new AppError("notFound.vehicle", HttpStatus.NOT_FOUND.value()));
    vehicleRepository.delete(existingVehicle);
  }

  public Boolean doesSingleVehicleExist(VehicleEntity vehicle) {
    String vehicleId = vehicle.getId();
    Optional<VehicleEntity> vehicleFound = vehicleRepository.findById(vehicleId);
    return vehicleFound.isPresent();
  }

  public Boolean doesMultipleVehiclesExist(List<VehicleEntity> vehicles) {
    List<String> vehicleIds = new ArrayList<>();
    vehicles.forEach(vehicle -> vehicleIds.add(vehicle.getId()));
    List<VehicleEntity> vehiclesFound = vehicleRepository.findAllById(vehicleIds);
    if (vehicles.size() != vehiclesFound.size()) {
      return false;
    }
    return vehiclesFound.isEmpty();
  }
}
