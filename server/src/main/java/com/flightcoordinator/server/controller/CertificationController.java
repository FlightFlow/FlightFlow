package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.model.CertificationModel;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.CertificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/certifications")
@Tag(name = "Certifications Controller", description = "Endpoints for managing Crew Members' certifications.")
public class CertificationController {
  @Autowired
  private CertificationService service;

  @GetMapping("/getById/{certificationId}")
  @Operation(summary = "Get a Certification By Id", description = "Retrieve a details of a spesific certification using it's ID.")
  public ResponseEntity<ResponseObject<CertificationModel>> getCertificationById(@PathVariable String certificationId) {
    CertificationModel certification = service.getSingleCertificationById(certificationId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(),
        certification);
  }

  @GetMapping("/getAll")
  public ResponseEntity<ResponseObject<List<CertificationModel>>> getAllCertification() {
    List<CertificationModel> certifications = service.getAllCertifications();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(),
        certifications);
  }

  @PostMapping("/create")
  public ResponseEntity<ResponseObject<Object>> createCertification(
      @RequestBody CertificationModel certification) {
    service.createCertification(certification);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @PatchMapping("/update/{certificationId}")
  public ResponseEntity<ResponseObject<Object>> updateCertification(@PathVariable String certificationId,
      @RequestBody CertificationModel certification) {
    service.updateCertification(certificationId, certification);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @DeleteMapping("delete/{certificationId}")
  public ResponseEntity<ResponseObject<Object>> deleteCertification(@PathVariable String certificationId) {
    service.deleteCertification(certificationId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

}
