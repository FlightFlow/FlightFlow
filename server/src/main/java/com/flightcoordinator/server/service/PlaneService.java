package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.PlaneDTO;
import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.entity.PlaneEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.repository.PlaneRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class PlaneService {
  @Autowired
  private PlaneRepository planeRepository;

  @Autowired
  private AirportRepository airportRepository;

  public PlaneDTO getSinglePlaneById(String planeId) {
    PlaneEntity plane = planeRepository.findById(planeId)
        .orElseThrow(() -> new AppError("notFound.plane", HttpStatus.NOT_FOUND.value()));
    PlaneDTO planeDTO = ObjectMapper.toPlaneDTO(plane);
    return planeDTO;
  }

  public List<PlaneDTO> getMultiplePlaneById(List<String> planeIds) {
    List<PlaneEntity> planes = planeRepository.findAllById(planeIds);
    if (planes.isEmpty()) {
      throw new AppError("notFound.plane", HttpStatus.NOT_FOUND.value());
    }
    List<PlaneDTO> planeDTOs = planes.stream().map(ObjectMapper::toPlaneDTO).collect(Collectors.toList());
    return planeDTOs;
  }

  public List<PlaneDTO> getAllPlanes() {
    List<PlaneEntity> planes = planeRepository.findAll();
    if (planes.isEmpty()) {
      throw new AppError("notFound.plane", HttpStatus.NOT_FOUND.value());
    }
    List<PlaneDTO> planeDTOs = planes.stream().map(ObjectMapper::toPlaneDTO).collect(Collectors.toList());
    return planeDTOs;
  }

  public void createPlane(PlaneDTO newPlaneDTO) {
    AirportEntity airportEntity = airportRepository.findById(newPlaneDTO.getCurrentLocationId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    PlaneEntity planeEntity = new PlaneEntity();
    planeEntity.setModel(newPlaneDTO.getModel());
    planeEntity.setRegistrationNumber(newPlaneDTO.getRegistrationNumber());
    planeEntity.setPassengerCapacity(newPlaneDTO.getPassengerCapacity());
    planeEntity.setFuelEfficiency(newPlaneDTO.getFuelEfficiency());
    planeEntity.setMaxFlightRange(newPlaneDTO.getMaxFlightRange());
    planeEntity.setLastMaintenance(newPlaneDTO.getLastMaintenance());
    planeEntity.setTotalFlightHours(newPlaneDTO.getTotalFlightHours());
    planeEntity.setMaxTakeoffWeight(newPlaneDTO.getMaxTakeoffWeight());
    planeEntity.setShortestRunwayLengthRequired(newPlaneDTO.getShortestRunwayLengthRequired());
    planeEntity.setShortestRunwayWidthRequired(newPlaneDTO.getShortestRunwayWidthRequired());
    planeEntity.setPlaneStatus(newPlaneDTO.getPlaneStatus());
    planeEntity.setCurrentLocation(airportEntity);
    planeEntity.setAircraftOperator(newPlaneDTO.getAircraftOperator());

    planeRepository.save(planeEntity);
  }

  public void updatePlane(String planeId, PlaneDTO updatedPlaneDTO) {
    AirportEntity newAirportEntity = airportRepository.findById(updatedPlaneDTO.getCurrentLocationId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    PlaneEntity existingPlane = planeRepository.findById(planeId)
        .orElseThrow(() -> new AppError("notFound.plane", HttpStatus.NOT_FOUND.value()));

    existingPlane.setModel(updatedPlaneDTO.getModel());
    existingPlane.setRegistrationNumber(updatedPlaneDTO.getRegistrationNumber());
    existingPlane.setPassengerCapacity(updatedPlaneDTO.getPassengerCapacity());
    existingPlane.setFuelEfficiency(updatedPlaneDTO.getFuelEfficiency());
    existingPlane.setMaxFlightRange(updatedPlaneDTO.getMaxFlightRange());
    existingPlane.setLastMaintenance(updatedPlaneDTO.getLastMaintenance());
    existingPlane.setTotalFlightHours(updatedPlaneDTO.getTotalFlightHours());
    existingPlane.setMaxTakeoffWeight(updatedPlaneDTO.getMaxTakeoffWeight());
    existingPlane.setShortestRunwayLengthRequired(updatedPlaneDTO.getShortestRunwayLengthRequired());
    existingPlane.setShortestRunwayWidthRequired(updatedPlaneDTO.getShortestRunwayWidthRequired());
    existingPlane.setPlaneStatus(updatedPlaneDTO.getPlaneStatus());
    existingPlane.setCurrentLocation(newAirportEntity);
    existingPlane.setAircraftOperator(updatedPlaneDTO.getAircraftOperator());

    planeRepository.save(existingPlane);
  }

  public void deletePlane(String planeId) {
    PlaneEntity existingPlane = planeRepository.findById(planeId)
        .orElseThrow(() -> new AppError("notFound.plane", HttpStatus.NOT_FOUND.value()));
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
