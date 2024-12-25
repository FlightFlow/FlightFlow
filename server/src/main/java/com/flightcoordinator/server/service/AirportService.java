package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.AirportDTO;
import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class AirportService {
  @Autowired
  private AirportRepository airportRepository;

  public AirportDTO getSingleAirportById(String airportId) {
    AirportEntity airport = airportRepository.findById(airportId)
        .orElseThrow(() -> new AppError("notFound.airport", HttpStatus.NOT_FOUND.value()));
    AirportDTO airportDto = ObjectMapper.toAirportDTO(airport);
    return airportDto;
  }

  public List<AirportDTO> getMultipleAirportsById(List<String> airportIds) {
    List<AirportEntity> airports = airportRepository.findAllById(airportIds);
    if (airports.isEmpty()) {
      throw new AppError("notFound.airport", HttpStatus.NOT_FOUND.value());
    }
    List<AirportDTO> airportDTOs = airports.stream().map(ObjectMapper::toAirportDTO).collect(Collectors.toList());
    return airportDTOs;
  }

  public List<AirportDTO> getAllAirports() {
    List<AirportEntity> airports = airportRepository.findAll();
    if (airports.isEmpty()) {
      throw new AppError("notFound.airport", HttpStatus.NOT_FOUND.value());
    }
    List<AirportDTO> airportDTOs = airports.stream().map(ObjectMapper::toAirportDTO).collect(Collectors.toList());
    return airportDTOs;
  }

  public void createAirport(AirportDTO newAirportDTO) {
    AirportEntity newAirportEntity = new AirportEntity();
    newAirportEntity.setName(newAirportDTO.getName());
    newAirportEntity.setIataCode(newAirportDTO.getIataCode());
    newAirportEntity.setIcaoCode(newAirportDTO.getIcaoCode());
    newAirportEntity.setCountryCode(newAirportDTO.getCountryCode());
    newAirportEntity.setType(newAirportDTO.getType());
    airportRepository.save(newAirportEntity);
  }

  public void updateAirport(String airportId, AirportDTO updatedAirportDTO) {
    AirportEntity existingAirport = airportRepository.findById(airportId)
        .orElseThrow(() -> new AppError("notFound.airport", HttpStatus.NOT_FOUND.value()));

    existingAirport.setName(updatedAirportDTO.getName());
    existingAirport.setIataCode(updatedAirportDTO.getIataCode());
    existingAirport.setIcaoCode(updatedAirportDTO.getIcaoCode());
    existingAirport.setCountryCode(updatedAirportDTO.getCountryCode());
    existingAirport.setType(updatedAirportDTO.getType());

    airportRepository.save(existingAirport);
  }

  public void deleteAirport(String airportId) {
    AirportEntity existingAirport = airportRepository.findById(airportId)
        .orElseThrow(() -> new AppError("notFound.airport", HttpStatus.NOT_FOUND.value()));
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
