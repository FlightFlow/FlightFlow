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

import com.flightcoordinator.server.entity.RouteEntity;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.RouteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${api.version}/route")
@Tag(name = "Route Controller", description = "Endpoints for managing routes.")
public class RouteController {
  @Autowired
  private RouteService routeService;

  @PostMapping("/getAll")
  @PreAuthorize("hasAuthority('ROUTE_READ')")
  @Operation(summary = "Get all the routes", description = "Retrieve the details of all routes.")
  public ResponseEntity<ResponseObject<List<RouteEntity>>> getAllRoutes() {
    List<RouteEntity> routes = routeService.getAllRoutes();
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", routes);
  }

  @PostMapping("/getById")
  @PreAuthorize("hasAuthority('ROUTE_READ')")
  @Operation(summary = "Get a route by id", description = "Retrieve the details of a spesific route using it's ID.")
  public ResponseEntity<ResponseObject<RouteEntity>> getRouteById(@RequestBody String routeId) {
    RouteEntity route = routeService.getSingleRouteById(routeId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.getResponse", route);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('ROUTE_CREATE')")
  @Operation(summary = "Create a new route", description = "Create a new route.")
  public ResponseEntity<ResponseObject<Object>> createRoute(@RequestBody RouteEntity newRoute) {
    routeService.createRoute(newRoute);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }

  @PatchMapping("/update")
  @PreAuthorize("hasAuthority('ROUTE_UPDATE')")
  @Operation(summary = "Update an route", description = "Update an existing route.")
  public ResponseEntity<ResponseObject<Object>> updateRoute(@RequestBody String routeId,
      @RequestBody RouteEntity updatedRoute) {
    routeService.updateRoute(routeId, updatedRoute);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.updateResponse", null);
  }

  @DeleteMapping("/delete")
  @PreAuthorize("hasAuthority('ROUTE_DELETE')")
  @Operation(summary = "Delete an route", description = "Delete an existing route.")
  public ResponseEntity<ResponseObject<Object>> deleteRoute(@RequestBody String routeId) {
    routeService.deleteRoute(routeId);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, "controllers.deleteResponse", null);
  }
}
