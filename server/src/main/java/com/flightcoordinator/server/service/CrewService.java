package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.model.CrewModel;
import com.flightcoordinator.server.repository.CrewRepository;

@Service
public class CrewService {
  @Autowired
  private CrewRepository repository;

  @Autowired
  private CertificationService certificationService;

  @Autowired
  private AirportService airportService;

  public CrewModel getSingleCrewMemberById(String crewMemberId) {
    Optional<CrewModel> crewMember = repository.findById(crewMemberId);
    return crewMember
        .orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<CrewModel> getMultipleCrewMemberById(List<String> crewMemberIds) {
    List<CrewModel> crewMembers = repository.findAllById(crewMemberIds);
    if (crewMembers.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return crewMembers;
  }

  public List<CrewModel> getAllCrewMembers() {
    List<CrewModel> crewMembers = repository.findAll();
    if (crewMembers.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return crewMembers;
  }

  public void createCrewMember(CrewModel newCrewMember) {
    Boolean doesCertificationsExist = certificationService
        .doesMultipleCertificationsExist(newCrewMember.getCertifications());
    Boolean doesBaseAirportExist = airportService.doesSingleAirportExist(newCrewMember.getBaseAirport());
    if (!doesCertificationsExist || !doesBaseAirportExist) {
      throw new AppError(doesCertificationsExist ? "Cannot validate certifications" : "Cannot validate base airport",
          HttpStatus.BAD_REQUEST.value());
    }
    Boolean doesPhoneNumberValid = newCrewMember.isPhoneNumberValid();
    if (doesPhoneNumberValid) {
      throw new AppError("Cannot validate phone number", HttpStatus.BAD_REQUEST.value());
    }
    repository.save(newCrewMember);
  }

  public void updateCrewMember(String crewMemberId, CrewModel updatedCrewMember) {
    Boolean doesCertificationsExist = certificationService
        .doesMultipleCertificationsExist(updatedCrewMember.getCertifications());
    Boolean doesBaseAirportExist = airportService.doesSingleAirportExist(updatedCrewMember.getBaseAirport());
    if (!doesCertificationsExist || !doesBaseAirportExist) {
      throw new AppError(doesCertificationsExist ? "Cannot validate certifications" : "Cannot validate base airport",
          HttpStatus.BAD_REQUEST.value());
    }
    Boolean doesPhoneNumberValid = updatedCrewMember.isPhoneNumberValid();
    if (doesPhoneNumberValid) {
      throw new AppError("Cannot validate phone number", HttpStatus.BAD_REQUEST.value());
    }

    CrewModel existingCrewMember = getSingleCrewMemberById(crewMemberId);

    existingCrewMember.setFullName(updatedCrewMember.getFullName());
    existingCrewMember.setEmail(updatedCrewMember.getEmail());
    existingCrewMember.setPhoneNumber(updatedCrewMember.getPhoneNumber());
    existingCrewMember.setRole(updatedCrewMember.getRole());
    existingCrewMember.setCertifications(updatedCrewMember.getCertifications());
    existingCrewMember.setTotalFlightHours(updatedCrewMember.getTotalFlightHours());
    existingCrewMember.setBaseAirport(updatedCrewMember.getBaseAirport());
    existingCrewMember.setAvailability(updatedCrewMember.getAvailability());

    repository.save(existingCrewMember);
  }

  public void deleteCrewMember(String crewMemberId) {
    CrewModel existingCrewMember = getSingleCrewMemberById(crewMemberId);
    repository.delete(existingCrewMember);
  }

  public Boolean doesSingleCrewMemberExist(String crewMemberId) {
    Optional<CrewModel> crewMember = repository.findById(crewMemberId);
    return crewMember.isPresent();
  }

  public Boolean doesMultipleCrewMemberExist(List<String> crewMemberIds) {
    List<CrewModel> crewMembers = repository.findAllById(crewMemberIds);
    return crewMembers.isEmpty();
  }
}
