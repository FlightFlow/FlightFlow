package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.PlaneEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.PlaneRepository;

@Service
public class PlaneService {
  @Autowired
  private PlaneRepository repository;

  @Autowired
  private AirportService airportService;

  public PlaneEntity getSinglePlaneById(String planeId) {
    Optional<PlaneEntity> plane = repository.findById(planeId);
    return plane.orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<PlaneEntity> getMultiplePlaneById(List<String> planeIds) {
    List<PlaneEntity> planes = repository.findAllById(planeIds);
    if (planes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return planes;
  }

  public List<PlaneEntity> getAllPlanes() {
    List<PlaneEntity> planes = repository.findAll();
    if (planes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return planes;
  }

  public void createPlane(PlaneEntity newPlane) {
    if (newPlane.getCurrentLocation() == null) {
      Boolean doesSingleAirportExist = airportService.doesSingleAirportExist(newPlane.getCurrentLocation());
      if (!doesSingleAirportExist) {
        throw new AppError("Cannot validate current location airport", HttpStatus.BAD_REQUEST.value());
      }
    }
    repository.save(newPlane);
  }

  public void updatePlane(String planeId, PlaneEntity updatedPlane) {
    if (updatedPlane.getCurrentLocation() == null) {
      Boolean doesSingleAirportExist = airportService.doesSingleAirportExist(updatedPlane.getCurrentLocation());
      if (!doesSingleAirportExist) {
        throw new AppError("Cannot validate current location airport", HttpStatus.BAD_REQUEST.value());
      }
    }

    PlaneEntity existingPlane = getSinglePlaneById(planeId);

    existingPlane.setModel(updatedPlane.getModel());
    existingPlane.setRegistrationNumber(updatedPlane.getRegistrationNumber());
    existingPlane.setPassengerCapacity(updatedPlane.getPassengerCapacity());
    existingPlane.setFuelEfficiency(updatedPlane.getFuelEfficiency());
    existingPlane.setMaxFlightRange(updatedPlane.getMaxFlightRange());
    existingPlane.setLastMaintenance(updatedPlane.getLastMaintenance());
    existingPlane.setTotalFlightHours(updatedPlane.getTotalFlightHours());
    existingPlane.setMaxTakeoffWeight(updatedPlane.getMaxTakeoffWeight());
    existingPlane.setShortestRunwayLengthRequired(updatedPlane.getShortestRunwayLengthRequired());
    existingPlane.setShortestRunwayWidthRequired(updatedPlane.getShortestRunwayWidthRequired());
    existingPlane.setPlaneStatus(updatedPlane.getPlaneStatus());
    existingPlane.setCurrentLocation(updatedPlane.getCurrentLocation());
    existingPlane.setAircraftOperator(updatedPlane.getAircraftOperator());

    repository.save(existingPlane);
  }

  public void deletePlane(String planeId) {
    PlaneEntity existingPlane = getSinglePlaneById(planeId);
    repository.delete(existingPlane);
  }
}
