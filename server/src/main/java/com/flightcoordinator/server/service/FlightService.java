package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.FlightEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.FlightRepository;

@Service
public class FlightService {
  @Autowired
  private FlightRepository flightRepository;

  public FlightEntity getSingleFlightById(String flightId) {
    Optional<FlightEntity> flight = flightRepository.findById(flightId);
    return flight.orElseThrow(() -> new AppError("notFound.flight", HttpStatus.NOT_FOUND.value()));
  }

  public List<FlightEntity> getMultipleFlightsById(List<String> flightIds) {
    List<FlightEntity> flights = flightRepository.findAllById(flightIds);
    if (flights.isEmpty()) {
      throw new AppError("notFound.flight", HttpStatus.NOT_FOUND.value());
    }
    return flights;
  }

  public List<FlightEntity> getAllFlights() {
    List<FlightEntity> flights = flightRepository.findAll();
    if (flights.isEmpty()) {
      throw new AppError("notFound.flight", HttpStatus.NOT_FOUND.value());
    }
    return flights;
  }

  public void createFlight(FlightEntity newFlight) {
    flightRepository.save(newFlight);
  }

  public void updateFlight(String flightId, FlightEntity updatedFlight) {
    FlightEntity existingFlight = getSingleFlightById(flightId);

    existingFlight.setPassengerCount(updatedFlight.getPassengerCount());
    existingFlight.setFlightRoute(updatedFlight.getFlightRoute());

    flightRepository.save(existingFlight);
  }

  public void deleteFlight(String flightId) {
    FlightEntity existingFlight = getSingleFlightById(flightId);
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
