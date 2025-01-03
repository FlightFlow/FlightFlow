package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.AlgorithmResultDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.entity.AlgorithmResultEntity;
import com.flightcoordinator.server.entity.CrewEntity;
import com.flightcoordinator.server.entity.FlightEntity;
import com.flightcoordinator.server.entity.PlaneEntity;
import com.flightcoordinator.server.entity.RunwayEntity;
import com.flightcoordinator.server.entity.VehicleEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AlgorithmResultRepository;
import com.flightcoordinator.server.repository.CrewRepository;
import com.flightcoordinator.server.repository.FlightRepository;
import com.flightcoordinator.server.repository.PlaneRepository;
import com.flightcoordinator.server.repository.RunwayRepository;
import com.flightcoordinator.server.repository.VehicleRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class AlgorithmResultService {
  @Autowired
  private AlgorithmResultRepository algorithmResultRepository;

  @Autowired
  private FlightRepository flightRepository;

  @Autowired
  private PlaneRepository planeRepository;

  @Autowired
  private CrewRepository crewRepository;

  @Autowired
  private RunwayRepository runwayRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  public AlgorithmResultDTO getSingleAlgorithmResultById(EntityIdDTO entityIdDTO) {
    AlgorithmResultEntity algorithmResult = algorithmResultRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.algorithmResult", HttpStatus.NOT_FOUND.value()));
    AlgorithmResultDTO algorithmResultDTO = ObjectMapper.toAlgorithmResultDTO(algorithmResult);
    return algorithmResultDTO;
  }

  public List<AlgorithmResultDTO> getMultipleAlgorithmResultsById(List<EntityIdDTO> entityIdDTOs) {
    List<AlgorithmResultEntity> algorithmResults = algorithmResultRepository.findAllById(
        entityIdDTOs.stream().map(
            entityId -> entityId.getId()).collect(Collectors.toList()));
    if (algorithmResults.isEmpty()) {
      throw new AppError("notFound.algorithmResult", HttpStatus.NOT_FOUND.value());
    }
    List<AlgorithmResultDTO> algorithmResultDTOs = algorithmResults.stream().map(ObjectMapper::toAlgorithmResultDTO)
        .collect(Collectors.toList());
    return algorithmResultDTOs;
  }

  public List<AlgorithmResultDTO> getAllAlgorithmResults() {
    List<AlgorithmResultEntity> algorithmResults = algorithmResultRepository.findAll();
    if (algorithmResults.isEmpty()) {
      throw new AppError("notFound.algorithmResult", HttpStatus.NOT_FOUND.value());
    }
    List<AlgorithmResultDTO> algorithmResultDTOs = algorithmResults.stream().map(ObjectMapper::toAlgorithmResultDTO)
        .collect(Collectors.toList());
    return algorithmResultDTOs;
  }

  public void createAlgorithmResult(AlgorithmResultDTO newAlgorithmResultDTO) {
    FlightEntity flightEntity = flightRepository.findById(newAlgorithmResultDTO.getFlightId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));
    PlaneEntity planeEntity = planeRepository.findById(newAlgorithmResultDTO.getPlaneId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));
    RunwayEntity takeoffRunwayEntity = runwayRepository.findById(newAlgorithmResultDTO.getTakeoffRunwayId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));
    RunwayEntity landingRunwayEntity = runwayRepository.findById(newAlgorithmResultDTO.getLandingRunwayId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    List<CrewEntity> crewMemberEntites = crewRepository.findAllById(newAlgorithmResultDTO.getCrewMemberIds());
    List<VehicleEntity> originAirportVehicleEntities = vehicleRepository
        .findAllById(newAlgorithmResultDTO.getOriginAirportGroundVehicleIds());
    List<VehicleEntity> destinationAirportVehicleEntities = vehicleRepository
        .findAllById(newAlgorithmResultDTO.getDestinationAirportGroundVehicleIds());

    if (crewMemberEntites.isEmpty() ||
        originAirportVehicleEntities.isEmpty() ||
        destinationAirportVehicleEntities.isEmpty()) {
      throw new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value());
    }

    AlgorithmResultEntity algorithmResultEntity = new AlgorithmResultEntity();

    algorithmResultEntity.setFlight(flightEntity);
    algorithmResultEntity.setPlane(planeEntity);
    algorithmResultEntity.setCrewMembers(crewMemberEntites);
    algorithmResultEntity.setTakeoffRunway(takeoffRunwayEntity);
    algorithmResultEntity.setLandingRunway(landingRunwayEntity);
    algorithmResultEntity.setOriginAirportGroundVehicles(originAirportVehicleEntities);
    algorithmResultEntity.setDestinationAirportGroundVehicles(destinationAirportVehicleEntities);

    algorithmResultRepository.save(algorithmResultEntity);
  }

  public void deleteAlgorithmResult(EntityIdDTO entityIdDTO) {
    AlgorithmResultEntity existingAlgorithmResult = algorithmResultRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.algorithmResult", HttpStatus.NOT_FOUND.value()));
    algorithmResultRepository.delete(existingAlgorithmResult);
  }

  public Boolean doesSingleAlgorithmResultExist(AlgorithmResultEntity algorithmResult) {
    String id = algorithmResult.getId();
    Optional<AlgorithmResultEntity> algorithmResultFound = algorithmResultRepository.findById(id);
    return algorithmResultFound.isPresent();
  }

  public Boolean doesMultipleAlgorithmResultsExist(List<AlgorithmResultEntity> algorithmResults) {
    List<String> ids = new ArrayList<>();
    algorithmResults.forEach(algorithmResult -> ids.add(algorithmResult.getId()));
    List<AlgorithmResultEntity> algorithmResultsFound = algorithmResultRepository.findAllById(ids);
    if (algorithmResults.size() != algorithmResultsFound.size()) {
      return false;
    }
    return algorithmResultsFound.isEmpty();
  }
}
