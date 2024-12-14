package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;

@Service
public class AirportService {
  @Autowired
  private AirportRepository airportRepository;

  @Autowired
  private RunwayService runwayService;

  public AirportEntity getSingleAirportById(String airportId) {
    Optional<AirportEntity> airport = airportRepository.findById(airportId);
    return airport.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<AirportEntity> getMultipleAirportsById(List<String> airportIds) {
    List<AirportEntity> airports = airportRepository.findAllById(airportIds);
    if (airports.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return airports;
  }

  public List<AirportEntity> getAllAirports() {
    List<AirportEntity> airports = airportRepository.findAll();
    if (airports.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return airports;
  }

  public void createAirport(AirportEntity newAirport) {
    Boolean doesRunwaysExist = runwayService.doesMultipleRunwaysExist(newAirport.getRunways());
    if (!doesRunwaysExist) {
      throw new AppError("Cannot validate runways", HttpStatus.BAD_REQUEST.value());
    }
    airportRepository.save(newAirport);
  }

  public void updateAirport(String airportId, AirportEntity updatedAirport) {
    Boolean doesRunwaysExist = runwayService.doesMultipleRunwaysExist(updatedAirport.getRunways());
    if (!doesRunwaysExist) {
      throw new AppError("Cannot validate runways", HttpStatus.BAD_REQUEST.value());
    }

    AirportEntity existingAirport = getSingleAirportById(airportId);

    existingAirport.setName(updatedAirport.getName());
    existingAirport.setIataCode(updatedAirport.getIataCode());
    existingAirport.setIcaoCode(updatedAirport.getIcaoCode());
    existingAirport.setCountryCode(updatedAirport.getCountryCode());
    existingAirport.setType(updatedAirport.getType());
    existingAirport.setRunways(updatedAirport.getRunways());

    airportRepository.save(existingAirport);
  }

  public void deleteAirport(String airportId) {
    AirportEntity existingAirport = getSingleAirportById(airportId);
    airportRepository.delete(existingAirport);
  }

  public Boolean doesSingleAirportExist(AirportEntity airport) {
    String airportId = airport.getId();
    Optional<AirportEntity> airportFound = airportRepository.findById(airportId);
    return airportFound.isPresent();
  }

  public Boolean doesMultipleAirportsExist(List<AirportEntity> airports) {
    List<String> airportIds = new ArrayList<>();
    airports.forEach(airport -> airportIds.add(airport.getId()));
    List<AirportEntity> airportsFound = airportRepository.findAllById(airportIds);
    if (airports.size() != airportsFound.size()) {
      return false;
    }
    return airportsFound.isEmpty();
  }
}
