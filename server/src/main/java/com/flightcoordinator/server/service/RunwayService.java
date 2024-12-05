package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.model.RunwayModel;
import com.flightcoordinator.server.repository.RunwayRepository;

@Service
public class RunwayService {
  @Autowired
  private RunwayRepository repository;

  public RunwayModel getSingleRunwayById(String runwayId) {
    Optional<RunwayModel> runway = repository.findById(runwayId);
    return runway.orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<RunwayModel> getMultipleRunwaysById(List<String> runwayIds) {
    List<RunwayModel> runways = repository.findAllById(runwayIds);
    if (runways.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return runways;
  }

  public List<RunwayModel> getAllAirports() {
    List<RunwayModel> runways = repository.findAll();
    if (runways.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return runways;
  }

  public void createRunway(RunwayModel newRunway) {
    repository.save(newRunway);
  }

  public void updateRunway(String runwayId, RunwayModel updatedRunway) {
    RunwayModel existingRunway = getSingleRunwayById(runwayId);

    existingRunway.setLength(updatedRunway.getLength());
    existingRunway.setWidth(updatedRunway.getWidth());
    existingRunway.getSurfaceType(updatedRunway.getSurfaceType());
    existingRunway.setMaxWeightCapacity(updatedRunway.getMaxWeightCapacity());
    existingRunway.setOrientation(updatedRunway.getOrientation());

    repository.save(existingRunway);
  }

  public void deleteRunway(String runwayId) {
    RunwayModel existingRunway = getSingleRunwayById(runwayId);
    repository.delete(existingRunway);
  }

  public Boolean doesSingleRunwayExist(String runwayId) {
    Optional<RunwayModel> airport = repository.findById(runwayId);
    return airport.isPresent();
  }

  public Boolean doesMultipleRunwaysExist(List<String> runwayIds) {
    List<RunwayModel> runways = repository.findAllById(runwayIds);
    return runways.isEmpty();
  }
}
