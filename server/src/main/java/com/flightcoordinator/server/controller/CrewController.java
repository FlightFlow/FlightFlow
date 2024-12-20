package com.flightcoordinator.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.entity.CrewEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.CrewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/crew")
@Tag(name = "Crew Controller", description = "Endpoints for managing crew members.")
public class CrewController {
  @Autowired
  private CrewService crewService;

  @PostMapping("/getAll")
  @PreAuthorize("hasAuthority('CREW_READ')")
  @Operation(summary = "Get a crew member by id", description = "Retrieve the details of a spesific crew member by their ID.")
  public ResponseEntity<ResponseObject<List<CrewEntity>>> getAllCrewMembers() {
    List<CrewEntity> crewMembers = crewService.getAllCrewMembers();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", crewMembers);
  }

  @PostMapping("/getById")
  @PreAuthorize("hasAuthority('CREW_READ')")
  @Operation(summary = "Get a crew member by id", description = "Retrieve the details of a spesific crew member by their ID.")
  public ResponseEntity<ResponseObject<CrewEntity>> getCrewMemberById(@RequestBody String crewMemberId) {
    CrewEntity crewMember = crewService.getSingleCrewMemberById(crewMemberId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", crewMember);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('CREW_CREATE')")
  @Operation(summary = "Create a new crew member", description = "Create a new crew member.")
  public ResponseEntity<ResponseObject<Object>> createCrewMember(@RequestBody CrewEntity newCrewMember) {
    crewService.createCrewMember(newCrewMember);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @PatchMapping("/update")
  @PreAuthorize("hasAuthority('CREW_UPDATE')")
  @Operation(summary = "Update an existing crew member", description = "Update an existing crew member.")
  public ResponseEntity<ResponseObject<Object>> updateCrewMember(@RequestBody String crewMemberId,
      @RequestBody CrewEntity updatedCrewMember) {
    crewService.updateCrewMember(crewMemberId, updatedCrewMember);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.updateResponse", null);
  }

  @DeleteMapping("/delete")
  @PreAuthorize("hasAuthority('CREW_DELETE')")
  @Operation(summary = "Delete an existing crew member", description = "Delete an existing crew member.")
  public ResponseEntity<ResponseObject<Object>> deleteCrewMember(@RequestBody String crewMemberId) {
    crewService.deleteCrewMember(crewMemberId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
