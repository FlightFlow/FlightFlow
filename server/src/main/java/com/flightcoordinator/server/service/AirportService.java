package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.model.AirportModel;
import com.flightcoordinator.server.repository.AirportRepository;

@Service
public class AirportService {
  @Autowired
  private AirportRepository repository;

  @Autowired
  private RunwayService runwayService;

  public AirportModel getSingleAirportById(String airportId) {
    Optional<AirportModel> airport = repository.findById(airportId);
    return airport
        .orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<AirportModel> getMultipleAirportsById(List<String> airportIds) {
    List<AirportModel> airports = repository.findAllById(airportIds);
    if (airports.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return airports;
  }

  public List<AirportModel> getAllAirports() {
    List<AirportModel> airports = repository.findAll();
    if (airports.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return airports;
  }

  public void createAirport(AirportModel newAirport) {
    Boolean doesRunwaysExist = runwayService.doesMultipleRunwaysExist(newAirport.getRunways());
    if (!doesRunwaysExist) {
      throw new AppError("Cannot validate runways", HttpStatus.BAD_REQUEST.value());
    }
    repository.save(newAirport);
  }

  public void updateAirport(String airportId, AirportModel updatedAirport) {
    Boolean doesRunwaysExist = runwayService.doesMultipleRunwaysExist(updatedAirport.getRunways());
    if (!doesRunwaysExist) {
      throw new AppError("Cannot validate runways", HttpStatus.BAD_REQUEST.value());
    }

    AirportModel existingAirport = getSingleAirportById(airportId);

    existingAirport.setName(updatedAirport.getName());
    existingAirport.setIataCode(updatedAirport.getIataCode());
    existingAirport.setIcaoCode(updatedAirport.getIcaoCode());
    existingAirport.setCountryCode(updatedAirport.getCountryCode());
    existingAirport.setType(updatedAirport.getType());
    existingAirport.setRunways(updatedAirport.getRunways());

    repository.save(existingAirport);
  }

  public void deleteAirport(String airportId) {
    AirportModel existingAirport = getSingleAirportById(airportId);
    repository.delete(existingAirport);
  }

  public Boolean doesSingleAirportExist(String airportId) {
    Optional<AirportModel> airport = repository.findById(airportId);
    return airport.isPresent();
  }

  public Boolean doesMultipleAirportsExist(List<String> airportIds) {
    List<AirportModel> airports = repository.findAllById(airportIds);
    return airports.isEmpty();
  }
}
