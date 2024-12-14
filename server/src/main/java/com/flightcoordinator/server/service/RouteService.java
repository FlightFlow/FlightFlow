package com.flightcoordinator.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.RouteEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.RouteRepository;

@Service
public class RouteService {
  @Autowired
  private RouteRepository routeRepository;

  @Autowired
  private AirportService airportService;

  public RouteEntity getSingleRouteById(String routeId) {
    Optional<RouteEntity> route = routeRepository.findById(routeId);
    return route.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<RouteEntity> getMultipleRouteById(List<String> routeIds) {
    List<RouteEntity> routes = routeRepository.findAllById(routeIds);
    if (routes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return routes;
  }

  public List<RouteEntity> getAllRoutes() {
    List<RouteEntity> routes = routeRepository.findAll();
    if (routes.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return routes;
  }

  public void createRoute(RouteEntity newRoute) {
    Boolean doesOriginAirportExist = airportService.doesSingleAirportExist(newRoute.getOriginAirport());
    Boolean doesDestinationAirportExist = airportService.doesSingleAirportExist(newRoute.getDestinationAirport());
    if (!doesOriginAirportExist || !doesDestinationAirportExist) {
      throw new AppError(
          doesOriginAirportExist ? "Cannot validate origin airport" : "Cannot validate destination airport",
          HttpStatus.BAD_REQUEST.value());
    }
    routeRepository.save(newRoute);
  }

  public void updateRoute(String routeId, RouteEntity newRoute) {
    Boolean doesOriginAirportExist = airportService.doesSingleAirportExist(newRoute.getOriginAirport());
    Boolean doesDestinationAirportExist = airportService.doesSingleAirportExist(newRoute.getDestinationAirport());
    if (!doesOriginAirportExist || !doesDestinationAirportExist) {
      throw new AppError(
          doesOriginAirportExist ? "Cannot validate origin airport" : "Cannot validate destination airport",
          HttpStatus.BAD_REQUEST.value());
    }

    RouteEntity existingRoute = getSingleRouteById(routeId);

    existingRoute.setOriginAirport(newRoute.getOriginAirport());
    existingRoute.setDestinationAirport(newRoute.getDestinationAirport());
    existingRoute.setDistance(newRoute.getDistance());
    existingRoute.setEstimatedTime(newRoute.getEstimatedTime());

    routeRepository.save(existingRoute);
  }

  public void deleteRoute(String routeId) {
    RouteEntity existingRoute = getSingleRouteById(routeId);
    routeRepository.delete(existingRoute);
  }
}
