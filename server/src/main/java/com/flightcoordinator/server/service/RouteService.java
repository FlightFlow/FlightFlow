package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.dto.RouteDTO;
import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.entity.RouteEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.repository.RouteRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class RouteService {
  @Autowired
  private RouteRepository routeRepository;

  @Autowired
  private AirportRepository airportRepository;

  public RouteDTO getSingleRouteById(EntityIdDTO entityIdDTO) {
    RouteEntity route = routeRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.route", HttpStatus.NOT_FOUND.value()));
    RouteDTO routeDTO = ObjectMapper.toRouteDTO(route);
    return routeDTO;
  }

  public List<RouteDTO> getMultipleRouteById(List<EntityIdDTO> entityIdDTOs) {
    List<RouteEntity> routes = routeRepository.findAllById(entityIdDTOs.stream().map(
        entityId -> entityId.getId()).collect(Collectors.toList()));
    if (routes.isEmpty()) {
      throw new AppError("notFound.route", HttpStatus.NOT_FOUND.value());
    }
    List<RouteDTO> routeDTOs = routes.stream().map(ObjectMapper::toRouteDTO).collect(Collectors.toList());
    return routeDTOs;
  }

  public List<RouteDTO> getAllRoutes() {
    List<RouteEntity> routes = routeRepository.findAll();
    if (routes.isEmpty()) {
      throw new AppError("notFound.route", HttpStatus.NOT_FOUND.value());
    }
    List<RouteDTO> routeDTOs = routes.stream().map(ObjectMapper::toRouteDTO).collect(Collectors.toList());
    return routeDTOs;
  }

  public void createRoute(RouteDTO newRouteDTO) {
    AirportEntity originAirportEntity = airportRepository.findById(newRouteDTO.getOriginAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));
    AirportEntity destinationAirportEntity = airportRepository.findById(newRouteDTO.getDestinationAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    RouteEntity newRouteEntity = new RouteEntity();
    newRouteEntity.setOriginAirport(originAirportEntity);
    newRouteEntity.setDestinationAirport(destinationAirportEntity);
    newRouteEntity.setDistance(newRouteDTO.getDistance());
    newRouteEntity.setEstimatedTime(newRouteDTO.getEstimatedTime());

    routeRepository.save(newRouteEntity);
  }

  public void updateRoute(RouteDTO updatedRouteDTO) {
    AirportEntity originAirportEntity = airportRepository.findById(updatedRouteDTO.getOriginAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));
    AirportEntity destinationAirportEntity = airportRepository.findById(updatedRouteDTO.getDestinationAirportId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    RouteEntity existingRoute = routeRepository.findById(updatedRouteDTO.getId())
        .orElseThrow(() -> new AppError("notFound.route", HttpStatus.NOT_FOUND.value()));

    existingRoute.setOriginAirport(originAirportEntity);
    existingRoute.setDestinationAirport(destinationAirportEntity);
    existingRoute.setDistance(updatedRouteDTO.getDistance());
    existingRoute.setEstimatedTime(updatedRouteDTO.getEstimatedTime());

    routeRepository.save(existingRoute);
  }

  public void deleteRoute(EntityIdDTO entityIdDTO) {
    RouteEntity existingRoute = routeRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.route", HttpStatus.NOT_FOUND.value()));
    routeRepository.delete(existingRoute);
  }

  public Boolean doesSingleRouteExist(RouteEntity route) {
    String id = route.getId();
    Optional<RouteEntity> routeFound = routeRepository.findById(id);
    return routeFound.isPresent();
  }

  public Boolean doesMultipleRoutesExist(List<RouteEntity> routes) {
    List<String> ids = new ArrayList<>();
    routes.forEach(route -> ids.add(route.getId()));
    List<RouteEntity> routesFound = routeRepository.findAllById(ids);
    if (routes.size() != routesFound.size()) {
      return false;
    }
    return routesFound.isEmpty();
  }
}
