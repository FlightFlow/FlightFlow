package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.CrewEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.CrewRepository;

@Service
public class CrewService {
  @Autowired
  private CrewRepository crewRepository;

  @Autowired
  private CertificationService certificationService;

  @Autowired
  private AirportService airportService;

  public CrewEntity getSingleCrewMemberById(String crewMemberId) {
    Optional<CrewEntity> crewMember = crewRepository.findById(crewMemberId);
    return crewMember.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<CrewEntity> getMultipleCrewMemberById(List<String> crewMemberIds) {
    List<CrewEntity> crewMembers = crewRepository.findAllById(crewMemberIds);
    if (crewMembers.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return crewMembers;
  }

  public List<CrewEntity> getAllCrewMembers() {
    List<CrewEntity> crewMembers = crewRepository.findAll();
    if (crewMembers.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return crewMembers;
  }

  public void createCrewMember(CrewEntity newCrewMember) {
    Boolean doesCertificationsExist = certificationService
        .doesMultipleCertificationsExist(newCrewMember.getCertifications());

    Boolean doesBaseAirportExist = airportService.doesSingleAirportExist(newCrewMember.getBaseAirport());

    if (!doesCertificationsExist || !doesBaseAirportExist) {
      throw new AppError(
          doesCertificationsExist ? "Cannot validate certifications" : "Cannot validate base airport",
          HttpStatus.BAD_REQUEST.value());
    }

    Boolean doesPhoneNumberValid;
    doesPhoneNumberValid = isPhoneNumberValid(newCrewMember.getPhoneNumber());
    if (doesPhoneNumberValid) {
      throw new AppError("Cannot validate phone number", HttpStatus.BAD_REQUEST.value());
    }

    crewRepository.save(newCrewMember);
  }

  public void updateCrewMember(String crewMemberId, CrewEntity updatedCrewMember) {
    Boolean doesCertificationsExist = certificationService
        .doesMultipleCertificationsExist(updatedCrewMember.getCertifications());

    Boolean doesBaseAirportExist = airportService.doesSingleAirportExist(updatedCrewMember.getBaseAirport());

    if (!doesCertificationsExist || !doesBaseAirportExist) {
      throw new AppError(
          doesCertificationsExist ? "Cannot validate certifications" : "Cannot validate base airport",
          HttpStatus.BAD_REQUEST.value());
    }

    Boolean doesPhoneNumberValid = isPhoneNumberValid(updatedCrewMember.getPhoneNumber());
    if (doesPhoneNumberValid) {
      throw new AppError("Cannot validate phone number", HttpStatus.BAD_REQUEST.value());
    }

    CrewEntity existingCrewMember = getSingleCrewMemberById(crewMemberId);

    existingCrewMember.setFullName(updatedCrewMember.getFullName());
    existingCrewMember.setEmail(updatedCrewMember.getEmail());
    existingCrewMember.setPhoneNumber(updatedCrewMember.getPhoneNumber());
    existingCrewMember.setRole(updatedCrewMember.getRole());
    existingCrewMember.setCertifications(updatedCrewMember.getCertifications());
    existingCrewMember.setTotalFlightHours(updatedCrewMember.getTotalFlightHours());
    existingCrewMember.setBaseAirport(updatedCrewMember.getBaseAirport());
    existingCrewMember.setAvailability(updatedCrewMember.getAvailability());

    crewRepository.save(existingCrewMember);
  }

  public void deleteCrewMember(String crewMemberId) {
    CrewEntity existingCrewMember = getSingleCrewMemberById(crewMemberId);
    crewRepository.delete(existingCrewMember);
  }

  private Boolean isPhoneNumberValid(Float phoneNumber) {
    return phoneNumber.toString().length() == 11;
  }
}
