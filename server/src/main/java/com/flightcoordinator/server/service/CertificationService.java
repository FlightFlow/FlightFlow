package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.CertificationDTO;
import com.flightcoordinator.server.entity.CertificationEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.CertificationRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class CertificationService {
  @Autowired
  private CertificationRepository certificationRepository;

  public CertificationDTO getSingleCertificationById(String certificationId) {
    CertificationEntity certification = certificationRepository.findById(certificationId)
        .orElseThrow(() -> new AppError("notFound.certification", HttpStatus.NOT_FOUND.value()));
    CertificationDTO certificationDTO = ObjectMapper.toCertificationDTO(certification);
    return certificationDTO;
  }

  public List<CertificationDTO> getMultipleCertificationsById(List<String> certificationIds) {
    List<CertificationEntity> certifications = certificationRepository.findAllById(certificationIds);
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

  public void createCertification(CertificationDTO newCertificationDTO) {
    CertificationEntity certificationEntity = new CertificationEntity();
    certificationEntity.setName(newCertificationDTO.getName());
    certificationEntity.setIssuer(newCertificationDTO.getIssuer());
    certificationEntity.setIssuingCountry(newCertificationDTO.getIssuingCountry());
    certificationEntity.setExpirationDate(newCertificationDTO.getExpirationDate());
    certificationEntity.setValidityPeriod(newCertificationDTO.getValidityPeriod());
    certificationEntity.setAssignableRoles(newCertificationDTO.getAssignableRoles());
    certificationEntity.setDescription(newCertificationDTO.getDescription());

    certificationRepository.save(certificationEntity);
  }

  public void updateCertification(String certificationId, CertificationDTO updatedCertificationDTO) {
    CertificationEntity existingCertification = certificationRepository.findById(certificationId)
        .orElseThrow(() -> new AppError("notFound.certification", HttpStatus.NOT_FOUND.value()));

    existingCertification.setName(updatedCertificationDTO.getName());
    existingCertification.setIssuer(updatedCertificationDTO.getIssuer());
    existingCertification.setIssuingCountry(updatedCertificationDTO.getIssuingCountry());
    existingCertification.setExpirationDate(updatedCertificationDTO.getExpirationDate());
    existingCertification.setValidityPeriod(updatedCertificationDTO.getValidityPeriod());
    existingCertification.setAssignableRoles(updatedCertificationDTO.getAssignableRoles());
    existingCertification.setDescription(updatedCertificationDTO.getDescription());

    certificationRepository.save(existingCertification);
  }

  public void deleteCertification(String certificationId) {
    CertificationEntity existingCertification = certificationRepository.findById(certificationId)
        .orElseThrow(() -> new AppError("notFound.certification", HttpStatus.NOT_FOUND.value()));
    certificationRepository.delete(existingCertification);
  }

  public Boolean doesSingleCertificationExist(CertificationEntity certification) {
    String certificationId = certification.getId();
    Optional<CertificationEntity> certificationFound = certificationRepository.findById(certificationId);
    return certificationFound.isPresent();
  }

  public Boolean doesMultipleCertificationsExist(List<CertificationEntity> certifications) {
    List<String> certificationIds = new ArrayList<>();
    certifications.forEach(certification -> certificationIds.add(certification.getId()));
    List<CertificationEntity> certificationsFound = certificationRepository.findAllById(certificationIds);
    if (certifications.size() != certificationsFound.size()) {
      return false;
    }
    return certificationsFound.isEmpty();
  }
}
