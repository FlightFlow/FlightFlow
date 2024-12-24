package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

  public FlightDTO getSingleFlightById(String flightId) {
    FlightEntity flight = flightRepository.findById(flightId)
        .orElseThrow(() -> new AppError("notFound.flight", HttpStatus.NOT_FOUND.value()));
    FlightDTO flightDTO = ObjectMapper.toFlightDTO(flight);
    return flightDTO;
  }

  public List<FlightDTO> getMultipleFlightsById(List<String> flightIds) {
    List<FlightEntity> flights = flightRepository.findAllById(flightIds);
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
    RouteEntity routeEntity = routeRepository.findById(newFlightDTO.getId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    FlightEntity flightEntity = new FlightEntity();
    flightEntity.setPassengerCount(newFlightDTO.getPassengerCount());
    flightEntity.setFlightRoute(routeEntity);

    flightRepository.save(flightEntity);
  }

  public void updateFlight(String flightId, FlightDTO updatedFlightDTO) {
    RouteEntity newRouteEntity = routeRepository.findById(updatedFlightDTO.getId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    FlightEntity existingFlight = flightRepository.findById(flightId)
        .orElseThrow(() -> new AppError("notFound.flight", HttpStatus.NOT_FOUND.value()));

    existingFlight.setPassengerCount(updatedFlightDTO.getPassengerCount());
    existingFlight.setFlightRoute(newRouteEntity);

    flightRepository.save(existingFlight);
  }

  public void deleteFlight(String flightId) {
    FlightEntity existingFlight = flightRepository.findById(flightId)
        .orElseThrow(() -> new AppError("notFound.flight", HttpStatus.NOT_FOUND.value()));
    flightRepository.delete(existingFlight);
  }

  public Boolean doesSingleFlightExist(FlightEntity flight) {
    String flightId = flight.getId();
    Optional<FlightEntity> flightFound = flightRepository.findById(flightId);
    return flightFound.isPresent();
  }

  public Boolean doesMultipleFlightsExist(List<FlightEntity> flights) {
    List<String> flightIds = new ArrayList<>();
    flights.forEach(flight -> flightIds.add(flight.getId()));
    List<FlightEntity> flightsFound = flightRepository.findAllById(flightIds);
    if (flights.size() != flightsFound.size()) {
      return false;
    }
    return flightsFound.isEmpty();
  }
}
