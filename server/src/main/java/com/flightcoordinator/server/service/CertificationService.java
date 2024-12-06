package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.model.CertificationModel;
import com.flightcoordinator.server.repository.CertificationRepository;

@Service
public class CertificationService {
  @Autowired
  private CertificationRepository repository;

  public CertificationModel getSingleCertificationById(String certificationId) {
    Optional<CertificationModel> certification = repository.findById(certificationId);
    return certification
        .orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<CertificationModel> getMultipleCertificationsById(List<String> certificationIds) {
    List<CertificationModel> certifications = repository.findAllById(certificationIds);
    if (certifications.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return certifications;
  }

  public List<CertificationModel> getAllCertifications() {
    List<CertificationModel> certifications = repository.findAll();
    if (certifications.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return certifications;
  }

  public void createCertification(CertificationModel newCertification) {
    repository.save(newCertification);
  }

  public void updateCertification(String certificationId, CertificationModel updatedCertification) {
    CertificationModel existingCertification = getSingleCertificationById(certificationId);

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
    CertificationModel existingCertification = getSingleCertificationById(certificationId);
    repository.delete(existingCertification);
  }

  public Boolean doesSingleCertificationExist(String certificationId) {
    Optional<CertificationModel> certification = repository.findById(certificationId);
    return certification.isPresent();
  }

  public Boolean doesMultipleCertificationsExist(List<String> certificationIds) {
    List<CertificationModel> certifications = repository.findAllById(certificationIds);
    if (certifications.size() != certificationIds.size()) {
      return false;
    }
    return certifications.isEmpty();
  }
}
