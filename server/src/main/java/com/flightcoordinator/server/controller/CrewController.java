package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.model.CrewModel;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.CrewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/crew")
@Tag(name = "Crew Controller", description = "Endpoints for managing crew members.")
public class CrewController {
  @Autowired
  private CrewService service;

  @GetMapping("/getById/{crewMemberId}")
  @Operation(summary = "Get a crew member by id", description = "Retrieve the details of a spesific crew member by their ID.")
  public ResponseEntity<ResponseObject<CrewModel>> getCrewMemberById(@PathVariable String crewMemberId) {
    CrewModel crewMember = service.getSingleCrewMemberById(crewMemberId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), crewMember);
  }

  @GetMapping("/getAll")
  @Operation(summary = "Get a crew member by id", description = "Retrieve the details of a spesific crew member by their ID.")
  public ResponseEntity<ResponseObject<List<CrewModel>>> getAllCrewMembers() {
    List<CrewModel> crewMembers = service.getAllCrewMembers();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), crewMembers);
  }

  @PostMapping("/create")
  @Operation(summary = "Create a new crew member", description = "Create a new crew member.")
  public ResponseEntity<ResponseObject<Object>> createCrewMember(@RequestBody CrewModel newCrewMember) {
    service.createCrewMember(newCrewMember);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PatchMapping("/update/{crewMemberId}")
  @Operation(summary = "Update an existing crew member", description = "Update an existing crew member.")
  public ResponseEntity<ResponseObject<Object>> updateCrewMember(@PathVariable String crewMemberId,
      @RequestBody CrewModel updatedCrewMember) {
    service.updateCrewMember(crewMemberId, updatedCrewMember);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @PatchMapping("/delete/{crewMemberId}")
  @Operation(summary = "Delete an existing crew member", description = "Delete an existing crew member.")
  public ResponseEntity<ResponseObject<Object>> deleteCrewMember(@PathVariable String crewMemberId) {
    service.deleteCrewMember(crewMemberId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
