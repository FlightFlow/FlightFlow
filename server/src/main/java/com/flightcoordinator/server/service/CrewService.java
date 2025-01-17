package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.CrewDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.create_update.CrewCreateUpdateDTO;
import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.entity.CrewEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.repository.CrewRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class CrewService {
  @Autowired
  private CrewRepository crewRepository;

  @Autowired
  private AirportRepository airportRepository;

  public CrewDTO getSingleCrewMemberById(EntityIdDTO entityIdDTO) {
    CrewEntity crewMember = crewRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.crew", HttpStatus.NOT_FOUND.value()));
    CrewDTO crewDTO = ObjectMapper.toCrewDTO(crewMember);
    return crewDTO;
  }

  public List<CrewDTO> getMultipleCrewMemberById(List<EntityIdDTO> entityIdDTOs) {
    List<CrewEntity> crewMembers = crewRepository.findAllById(entityIdDTOs.stream().map(
        entityId -> entityId.getId()).collect(Collectors.toList()));
    if (crewMembers.isEmpty()) {
      throw new AppError("notFound.crew", HttpStatus.NOT_FOUND.value());
    }
    List<CrewDTO> crewDTOs = crewMembers.stream().map(ObjectMapper::toCrewDTO).collect(Collectors.toList());
    return crewDTOs;
  }

  public List<CrewDTO> getAllCrewMembers() {
    List<CrewEntity> crewMembers = crewRepository.findAll();
    if (crewMembers.isEmpty()) {
      throw new AppError("notFound.crew", HttpStatus.NOT_FOUND.value());
    }
    List<CrewDTO> crewDTOs = crewMembers.stream().map(ObjectMapper::toCrewDTO).collect(Collectors.toList());
    return crewDTOs;
  }

  public void createCrewMember(CrewCreateUpdateDTO newCrewMemberDTO) {
    AirportEntity airportEntity = airportRepository.findById(newCrewMemberDTO.getBaseAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    CrewEntity crewEntity = new CrewEntity();
    crewEntity.setFullName(newCrewMemberDTO.getFullName());
    crewEntity.setEmail(newCrewMemberDTO.getEmail());
    crewEntity.setPhoneNumber(newCrewMemberDTO.getPhoneNumber());
    crewEntity.setRole(newCrewMemberDTO.getRole());
    crewEntity.setBaseAirport(airportEntity);
    crewEntity.setTotalFlightHours(newCrewMemberDTO.getTotalFlightHours());
    crewEntity.setAvailability(newCrewMemberDTO.getAvailability());

    crewRepository.save(crewEntity);
  }

  public void updateCrewMember(CrewCreateUpdateDTO updatedCrewMemberDTO) {
    Boolean doesPhoneNumberValid = isPhoneNumberValid(updatedCrewMemberDTO.getPhoneNumber());
    if (doesPhoneNumberValid) {
      throw new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value());
    }

    CrewEntity existingCrewMember = crewRepository.findById(updatedCrewMemberDTO.getId())
        .orElseThrow(() -> new AppError("notFound.crew", HttpStatus.NOT_FOUND.value()));

    existingCrewMember.setFullName(updatedCrewMemberDTO.getFullName());
    existingCrewMember.setEmail(updatedCrewMemberDTO.getEmail());
    existingCrewMember.setPhoneNumber(updatedCrewMemberDTO.getPhoneNumber());
    existingCrewMember.setRole(updatedCrewMemberDTO.getRole());
    existingCrewMember.setTotalFlightHours(updatedCrewMemberDTO.getTotalFlightHours());
    existingCrewMember.setAvailability(updatedCrewMemberDTO.getAvailability());

    crewRepository.save(existingCrewMember);
  }

  public void deleteCrewMember(EntityIdDTO entityIdDTO) {
    CrewEntity existingCrewMember = crewRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.crew", HttpStatus.NOT_FOUND.value()));
    crewRepository.delete(existingCrewMember);
  }

  private Boolean isPhoneNumberValid(Long phoneNumber) {
    return phoneNumber.toString().length() == 11;
  }

  public Boolean doesSingleCrewExist(CrewEntity crewMember) {
    String id = crewMember.getId();
    Optional<CrewEntity> crewMemberFound = crewRepository.findById(id);
    return crewMemberFound.isPresent();
  }

  public Boolean doesMultipleCrewsExist(List<CrewEntity> crewMembers) {
    List<String> ids = new ArrayList<>();
    crewMembers.forEach(crewMember -> ids.add(crewMember.getId()));
    List<CrewEntity> crewMembersFound = crewRepository.findAllById(ids);
    if (crewMembers.size() != crewMembersFound.size()) {
      return false;
    }
    return crewMembersFound.isEmpty();
  }
}
