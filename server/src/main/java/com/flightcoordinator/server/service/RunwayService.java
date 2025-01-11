package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.RunwayDTO;
import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.entity.RunwayEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.repository.RunwayRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class RunwayService {
  @Autowired
  private RunwayRepository runwayRepository;

  @Autowired
  private AirportRepository airportRepository;

  public RunwayDTO getSingleRunwayById(EntityIdDTO entityIdDTO) {
    RunwayEntity runway = runwayRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.runway", HttpStatus.NOT_FOUND.value()));
    RunwayDTO runwayDTO = ObjectMapper.toRunwayDTO(runway);
    return runwayDTO;
  }

  public List<RunwayDTO> getMultipleRunwaysById(List<EntityIdDTO> entityIdDTOs) {
    List<RunwayEntity> runways = runwayRepository.findAllById(entityIdDTOs.stream().map(
        entityId -> entityId.getId()).collect(Collectors.toList()));
    if (runways.isEmpty()) {
      throw new AppError("notFound.runway", HttpStatus.NOT_FOUND.value());
    }
    List<RunwayDTO> runwayDTOs = runways.stream().map(ObjectMapper::toRunwayDTO).collect(Collectors.toList());
    return runwayDTOs;
  }

  public List<RunwayDTO> getAllRunways() {
    List<RunwayEntity> runways = runwayRepository.findAll();
    if (runways.isEmpty()) {
      throw new AppError("notFound.runway", HttpStatus.NOT_FOUND.value());
    }
    List<RunwayDTO> runwayDTOs = runways.stream().map(ObjectMapper::toRunwayDTO).collect(Collectors.toList());
    return runwayDTOs;
  }

  public void createRunway(RunwayDTO newRunwayDTO) {
    AirportEntity airportEntity = airportRepository.findById(newRunwayDTO.getAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    RunwayEntity newRunwayEntity = new RunwayEntity();
    newRunwayEntity.setLength(newRunwayDTO.getLength());
    newRunwayEntity.setWidth(newRunwayDTO.getWidth());
    newRunwayEntity.setSurfaceType(newRunwayDTO.getSurfaceType());
    newRunwayEntity.setMaxWeightCapacity(newRunwayDTO.getMaxWeightCapacity());
    newRunwayEntity.setOrientation(newRunwayDTO.getOrientation());
    newRunwayEntity.setAirport(airportEntity);

    runwayRepository.save(newRunwayEntity);
  }

  public void updateRunway(RunwayDTO updatedRunwayDTO) {
    AirportEntity airportEntity = airportRepository.findById(updatedRunwayDTO.getAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    RunwayEntity existingRunway = runwayRepository.findById(updatedRunwayDTO.getId())
        .orElseThrow(() -> new AppError("notFound.runway", HttpStatus.NOT_FOUND.value()));

    existingRunway.setLength(updatedRunwayDTO.getLength());
    existingRunway.setWidth(updatedRunwayDTO.getWidth());
    existingRunway.setSurfaceType(updatedRunwayDTO.getSurfaceType());
    existingRunway.setMaxWeightCapacity(updatedRunwayDTO.getMaxWeightCapacity());
    existingRunway.setOrientation(updatedRunwayDTO.getOrientation());
    existingRunway.setAirport(airportEntity);

    runwayRepository.save(existingRunway);
  }

  public void deleteRunway(EntityIdDTO entityIdDTO) {
    RunwayEntity existingRunway = runwayRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.runway", HttpStatus.NOT_FOUND.value()));
    runwayRepository.delete(existingRunway);
  }

  public Boolean doesSingleRunwayExist(RunwayEntity runway) {
    String id = runway.getId();
    Optional<RunwayEntity> runwayFound = runwayRepository.findById(id);
    return runwayFound.isPresent();
  }

  public Boolean doesMultipleRunwaysExist(List<RunwayEntity> runways) {
    List<String> ids = new ArrayList<>();
    runways.forEach(runway -> ids.add(runway.getId()));
    List<RunwayEntity> runwaysFound = runwayRepository.findAllById(ids);
    if (runways.size() != runwaysFound.size()) {
      return false;
    }
    return runwaysFound.isEmpty();
  }
}
