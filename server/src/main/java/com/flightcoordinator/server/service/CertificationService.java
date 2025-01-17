package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.CertificationDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.create_update.CertificationCreateUpdateDTO;
import com.flightcoordinator.server.entity.CertificationEntity;
import com.flightcoordinator.server.entity.CrewEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.CertificationRepository;
import com.flightcoordinator.server.repository.CrewRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class CertificationService {
  @Autowired
  private CertificationRepository certificationRepository;

  @Autowired
  private CrewRepository crewRepository;

  public CertificationDTO getSingleCertificationById(EntityIdDTO entityIdDTO) {
    CertificationEntity certification = certificationRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.certification", HttpStatus.NOT_FOUND.value()));
    CertificationDTO certificationDTO = ObjectMapper.toCertificationDTO(certification);
    return certificationDTO;
  }

  public List<CertificationDTO> getMultipleCertificationsById(List<EntityIdDTO> entityIdDTOs) {
    List<CertificationEntity> certifications = certificationRepository.findAllById(entityIdDTOs.stream().map(
        entityId -> entityId.getId()).collect(Collectors.toList()));
    if (certifications.isEmpty()) {
      throw new AppError("notFound.certification", HttpStatus.NOT_FOUND.value());
    }
    List<CertificationDTO> certificationDTOs = certifications.stream().map(ObjectMapper::toCertificationDTO)
        .collect(Collectors.toList());
    return certificationDTOs;
  }

  public List<CertificationDTO> getAllCertifications() {
    List<CertificationEntity> certifications = certificationRepository.findAll();
    if (certifications.isEmpty()) {
      throw new AppError("notFound.certification", HttpStatus.NOT_FOUND.value());
    }
    List<CertificationDTO> certificationDTOs = certifications.stream().map(ObjectMapper::toCertificationDTO)
        .collect(Collectors.toList());
    return certificationDTOs;
  }

  public void createCertification(CertificationCreateUpdateDTO newCertificationDTO) {
    CrewEntity crewEntity = crewRepository.findById(newCertificationDTO.getAssignedCrewMemberId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    CertificationEntity certificationEntity = new CertificationEntity();
    certificationEntity.setName(newCertificationDTO.getName());
    certificationEntity.setIssuer(newCertificationDTO.getIssuer());
    certificationEntity.setIssuingCountry(newCertificationDTO.getIssuingCountry());
    certificationEntity.setExpirationDate(newCertificationDTO.getExpirationDate());
    certificationEntity.setValidityPeriod(newCertificationDTO.getValidityPeriod());
    certificationEntity.setAssignableRole(newCertificationDTO.getAssignableRole());
    certificationEntity.setAssignedCrewMember(crewEntity);
    certificationEntity.setDescription(newCertificationDTO.getDescription());

    certificationRepository.save(certificationEntity);
  }

  public void updateCertification(CertificationCreateUpdateDTO updatedCertificationDTO) {
    CertificationEntity existingCertification = certificationRepository.findById(updatedCertificationDTO.getId())
        .orElseThrow(() -> new AppError("notFound.certification", HttpStatus.NOT_FOUND.value()));

    existingCertification.setName(updatedCertificationDTO.getName());
    existingCertification.setIssuer(updatedCertificationDTO.getIssuer());
    existingCertification.setIssuingCountry(updatedCertificationDTO.getIssuingCountry());
    existingCertification.setExpirationDate(updatedCertificationDTO.getExpirationDate());
    existingCertification.setValidityPeriod(updatedCertificationDTO.getValidityPeriod());
    existingCertification.setAssignableRole(updatedCertificationDTO.getAssignableRole());
    existingCertification.setDescription(updatedCertificationDTO.getDescription());

    certificationRepository.save(existingCertification);
  }

  public void deleteCertification(EntityIdDTO entityIdDTO) {
    CertificationEntity existingCertification = certificationRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.certification", HttpStatus.NOT_FOUND.value()));
    certificationRepository.delete(existingCertification);
  }

  public Boolean doesSingleCertificationExist(CertificationEntity certification) {
    String id = certification.getId();
    Optional<CertificationEntity> certificationFound = certificationRepository.findById(id);
    return certificationFound.isPresent();
  }

  public Boolean doesMultipleCertificationsExist(List<CertificationEntity> certifications) {
    List<String> ids = new ArrayList<>();
    certifications.forEach(certification -> ids.add(certification.getId()));
    List<CertificationEntity> certificationsFound = certificationRepository.findAllById(ids);
    if (certifications.size() != certificationsFound.size()) {
      return false;
    }
    return certificationsFound.isEmpty();
  }
}
