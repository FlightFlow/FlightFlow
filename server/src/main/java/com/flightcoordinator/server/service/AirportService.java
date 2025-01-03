package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.AirportDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.create_update.AirportCreateUpdateDTO;
import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class AirportService {
  @Autowired
  private AirportRepository airportRepository;

  public AirportDTO getSingleAirportById(EntityIdDTO entityIdDTO) {
    AirportEntity airport = airportRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.airport", HttpStatus.NOT_FOUND.value()));
    AirportDTO airportDto = ObjectMapper.toAirportDTO(airport);
    return airportDto;
  }

  public List<AirportDTO> getMultipleAirportsById(List<EntityIdDTO> entityIdDTOs) {
    List<AirportEntity> airports = airportRepository.findAllById(entityIdDTOs.stream().map(
        entityId -> entityId.getId()).collect(Collectors.toList()));
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

  public void createAirport(AirportCreateUpdateDTO newAirportDTO) {
    AirportEntity newAirportEntity = new AirportEntity();

    newAirportEntity.setName(newAirportDTO.getName());
    newAirportEntity.setIataCode(newAirportDTO.getIataCode());
    newAirportEntity.setIcaoCode(newAirportDTO.getIcaoCode());
    newAirportEntity.setCountryCode(newAirportDTO.getCountryCode());
    newAirportEntity.setType(newAirportDTO.getType());

    airportRepository.save(newAirportEntity);
  }

  public void updateAirport(AirportCreateUpdateDTO updatedAirportDTO) {
    AirportEntity existingAirport = airportRepository.findById(updatedAirportDTO.getId())
        .orElseThrow(() -> new AppError("notFound.airport", HttpStatus.NOT_FOUND.value()));

    existingAirport.setName(updatedAirportDTO.getName());
    existingAirport.setIataCode(updatedAirportDTO.getIataCode());
    existingAirport.setIcaoCode(updatedAirportDTO.getIcaoCode());
    existingAirport.setCountryCode(updatedAirportDTO.getCountryCode());
    existingAirport.setType(updatedAirportDTO.getType());

    airportRepository.save(existingAirport);
  }

  public void deleteAirport(EntityIdDTO entityIdDTO) {
    AirportEntity existingAirport = airportRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.airport", HttpStatus.NOT_FOUND.value()));
    airportRepository.delete(existingAirport);
  }

  public Boolean doesSingleAirportExist(AirportEntity airport) {
    String id = airport.getId();
    Optional<AirportEntity> airportFound = airportRepository.findById(id);
    return airportFound.isPresent();
  }

  public Boolean doesMultipleAirportsExist(List<AirportEntity> airports) {
    List<String> ids = new ArrayList<>();
    airports.forEach(airport -> ids.add(airport.getId()));
    List<AirportEntity> airportsFound = airportRepository.findAllById(ids);
    if (airports.size() != airportsFound.size()) {
      return false;
    }
    return airportsFound.isEmpty();
  }
}
