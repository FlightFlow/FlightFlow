package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.FlightDTO;
import com.flightcoordinator.server.entity.FlightEntity;
import com.flightcoordinator.server.entity.RouteEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.FlightRepository;
import com.flightcoordinator.server.repository.RouteRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class FlightService {
  @Autowired
  private FlightRepository flightRepository;

  @Autowired
  private RouteRepository routeRepository;

  public FlightDTO getSingleFlightById(EntityIdDTO entityIdDTO) {
    FlightEntity flight = flightRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.flight", HttpStatus.NOT_FOUND.value()));
    FlightDTO flightDTO = ObjectMapper.toFlightDTO(flight);
    return flightDTO;
  }

  public List<FlightDTO> getMultipleFlightsById(List<EntityIdDTO> entityIdDTOs) {
    List<FlightEntity> flights = flightRepository.findAllById(entityIdDTOs.stream().map(
        entityId -> entityId.getId()).collect(Collectors.toList()));
    if (flights.isEmpty()) {
      throw new AppError("notFound.flight", HttpStatus.NOT_FOUND.value());
    }
    List<FlightDTO> flightDTOs = flights.stream().map(ObjectMapper::toFlightDTO).collect(Collectors.toList());
    return flightDTOs;
  }

  public List<FlightDTO> getAllFlights() {
    List<FlightEntity> flights = flightRepository.findAll();
    if (flights.isEmpty()) {
      throw new AppError("notFound.flight", HttpStatus.NOT_FOUND.value());
    }
    List<FlightDTO> flightDTOs = flights.stream().map(ObjectMapper::toFlightDTO).collect(Collectors.toList());
    return flightDTOs;
  }

  public void createFlight(FlightDTO newFlightDTO) {
    RouteEntity routeEntity = routeRepository.findById(newFlightDTO.getFlightRouteId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    FlightEntity flightEntity = new FlightEntity();
    flightEntity.setPassengerCount(newFlightDTO.getPassengerCount());
    flightEntity.setFlightRoute(routeEntity);

    flightRepository.save(flightEntity);
  }

  public void updateFlight(FlightDTO updatedFlightDTO) {
    RouteEntity newRouteEntity = routeRepository.findById(updatedFlightDTO.getFlightRouteId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    FlightEntity existingFlight = flightRepository.findById(updatedFlightDTO.getId())
        .orElseThrow(() -> new AppError("notFound.flight", HttpStatus.NOT_FOUND.value()));

    existingFlight.setPassengerCount(updatedFlightDTO.getPassengerCount());
    existingFlight.setFlightRoute(newRouteEntity);

    flightRepository.save(existingFlight);
  }

  public void deleteFlight(EntityIdDTO entityIdDTO) {
    FlightEntity existingFlight = flightRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.flight", HttpStatus.NOT_FOUND.value()));
    flightRepository.delete(existingFlight);
  }

  public Boolean doesSingleFlightExist(FlightEntity flight) {
    String id = flight.getId();
    Optional<FlightEntity> flightFound = flightRepository.findById(id);
    return flightFound.isPresent();
  }

  public Boolean doesMultipleFlightsExist(List<FlightEntity> flights) {
    List<String> ids = new ArrayList<>();
    flights.forEach(flight -> ids.add(flight.getId()));
    List<FlightEntity> flightsFound = flightRepository.findAllById(ids);
    if (flights.size() != flightsFound.size()) {
      return false;
    }
    return flightsFound.isEmpty();
  }
}
