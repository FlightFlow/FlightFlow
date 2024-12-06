package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.model.RouteModel;
import com.flightcoordinator.server.repository.RouteRepository;

@Service
public class RouteService {
  @Autowired
  private RouteRepository repository;

  @Autowired
  private AirportService airportService;

  public RouteModel getSingleRouteById(String routeId) {
    Optional<RouteModel> route = repository.findById(routeId);
    return route.orElseThrow(() -> new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));
  }

  public List<RouteModel> getMultipleRouteById(List<String> routeIds) {
    List<RouteModel> routes = repository.findAllById(routeIds);
    if (routes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return routes;
  }

  public List<RouteModel> getAllRoutes() {
    List<RouteModel> routes = repository.findAll();
    if (routes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return routes;
  }

  public void createRoute(RouteModel newRoute) {
    Boolean doesOriginAirportExist = airportService.doesSingleAirportExist(newRoute.getOriginAirportId());
    Boolean doesDestinationAirportExist = airportService.doesSingleAirportExist(newRoute.getDestinationAirportId());
    if (!doesOriginAirportExist || !doesDestinationAirportExist) {
      throw new AppError(
          doesOriginAirportExist ? "Cannot validate origin airport" : "Cannot validate destination airport",
          HttpStatus.BAD_REQUEST.value());
    }
    repository.save(newRoute);
  }

  public void updateRoute(String routeId, RouteModel newRoute) {
    Boolean doesOriginAirportExist = airportService.doesSingleAirportExist(newRoute.getOriginAirportId());
    Boolean doesDestinationAirportExist = airportService.doesSingleAirportExist(newRoute.getDestinationAirportId());
    if (!doesOriginAirportExist || !doesDestinationAirportExist) {
      throw new AppError(
          doesOriginAirportExist ? "Cannot validate origin airport" : "Cannot validate destination airport",
          HttpStatus.BAD_REQUEST.value());
    }

    RouteModel existingRoute = getSingleRouteById(routeId);

    existingRoute.setOriginAirportId(newRoute.getOriginAirportId());
    existingRoute.setDestinationAirportId(newRoute.getDestinationAirportId());
    existingRoute.setDistance(newRoute.getDistance());
    existingRoute.setEstimatedTime(newRoute.getEstimatedTime());

    repository.save(existingRoute);
  }

  public void deleteRoute(String routeId) {
    RouteModel existingRoute = getSingleRouteById(routeId);
    repository.delete(existingRoute);
  }

  public Boolean doesSingleRouteExist(String routeId) {
    Optional<RouteModel> route = repository.findById(routeId);
    return route.isPresent();
  }

  public Boolean doesMultipleRouteExist(List<String> routeIds) {
    List<RouteModel> routes = repository.findAllById(routeIds);
    if (routes.size() != routeIds.size()) {
      return false;
    }
    return routes.isEmpty();
  }
}
