package com.flightcoordinator.server.service;

import java.util.ArrayList;
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
  private PlaneRepository planeRepository;

  @Autowired
  private AirportService airportService;

  public PlaneEntity getSinglePlaneById(String planeId) {
    Optional<PlaneEntity> plane = planeRepository.findById(planeId);
    return plane.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<PlaneEntity> getMultiplePlaneById(List<String> planeIds) {
    List<PlaneEntity> planes = planeRepository.findAllById(planeIds);
    if (planes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return planes;
  }

  public List<PlaneEntity> getAllPlanes() {
    List<PlaneEntity> planes = planeRepository.findAll();
    if (planes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return planes;
  }

  public void createPlane(PlaneEntity newPlane) {
    if (newPlane.getCurrentLocation() == null) {
      Boolean doesSingleAirportExist = airportService.doesSingleAirportExist(newPlane.getCurrentLocation());
      if (!doesSingleAirportExist) {
        throw new AppError("Cannot validate current location plane", HttpStatus.BAD_REQUEST.value());
      }
    }
    planeRepository.save(newPlane);
  }

  public void updatePlane(String planeId, PlaneEntity updatedPlane) {
    if (updatedPlane.getCurrentLocation() == null) {
      Boolean doesSingleAirportExist = airportService.doesSingleAirportExist(updatedPlane.getCurrentLocation());
      if (!doesSingleAirportExist) {
        throw new AppError("Cannot validate current location plane", HttpStatus.BAD_REQUEST.value());
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

    planeRepository.save(existingPlane);
  }

  public void deletePlane(String planeId) {
    PlaneEntity existingPlane = getSinglePlaneById(planeId);
    planeRepository.delete(existingPlane);
  }

  public Boolean doesSinglePlaneExist(PlaneEntity plane) {
    String planeId = plane.getId();
    Optional<PlaneEntity> planeFound = planeRepository.findById(planeId);
    return planeFound.isPresent();
  }

  public Boolean doesMultiplePlanesExist(List<PlaneEntity> planes) {
    List<String> planeIds = new ArrayList<>();
    planes.forEach(plane -> planeIds.add(plane.getId()));
    List<PlaneEntity> planesFound = planeRepository.findAllById(planeIds);
    if (planes.size() != planesFound.size()) {
      return false;
    }
    return planesFound.isEmpty();
  }
}
