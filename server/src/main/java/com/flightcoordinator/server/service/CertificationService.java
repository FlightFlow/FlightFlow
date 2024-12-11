package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.CertificationEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.CertificationRepository;

@Service
public class CertificationService {
  @Autowired
  private CertificationRepository repository;

  public CertificationEntity getSingleCertificationById(String certificationId) {
    Optional<CertificationEntity> certification = repository.findById(certificationId);
    return certification
        .orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<CertificationEntity> getMultipleCertificationsById(List<String> certificationIds) {
    List<CertificationEntity> certifications = repository.findAllById(certificationIds);
    if (certifications.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return certifications;
  }

  public List<CertificationEntity> getAllCertifications() {
    List<CertificationEntity> certifications = repository.findAll();
    if (certifications.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return certifications;
  }

  public void createCertification(CertificationEntity newCertification) {
    repository.save(newCertification);
  }

  public void updateCertification(String certificationId, CertificationEntity updatedCertification) {
    CertificationEntity existingCertification = getSingleCertificationById(certificationId);

    existingCertification.setName(updatedCertification.getName());
    existingCertification.setIssuer(updatedCertification.getIssuer());
    existingCertification.setIssuingCountry(updatedCertification.getIssuingCountry());
    existingCertification.setExpirationDate(updatedCertification.getExpirationDate());
    existingCertification.setValidityPeriod(updatedCertification.getValidityPeriod());
    existingCertification.setAssignableRoles(updatedCertification.getAssignableRoles());
    existingCertification.setDescription(updatedCertification.getDescription());

    repository.save(existingCertification);
  }

  public void deleteCertification(String certificationId) {
    CertificationEntity existingCertification = getSingleCertificationById(certificationId);
    repository.delete(existingCertification);
  }

  public Boolean doesSingleCertificationExist(String certificationId) {
    Optional<CertificationEntity> certification = repository.findById(certificationId);
    return certification.isPresent();
  }

  public Boolean doesMultipleCertificationsExist(List<CertificationEntity> certifications) {
    List<Boolean> checkedCertifications = new ArrayList<>();
    for (CertificationEntity certification : certifications) {
      Optional<CertificationEntity> currentCertification = repository.findById(certification.getId());
      if (currentCertification.isPresent()) {
        checkedCertifications.add(true);
      }
    }
    if (certifications.size() != checkedCertifications.size()) {
      return false;
    }
    return certifications.isEmpty();
  }
}
