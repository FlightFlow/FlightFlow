package com.flightcoordinator.server.devtools;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.entity.CertificationEntity;
import com.flightcoordinator.server.entity.CrewEntity;
import com.flightcoordinator.server.entity.FlightEntity;
import com.flightcoordinator.server.entity.PlaneEntity;
import com.flightcoordinator.server.entity.RouteEntity;
import com.flightcoordinator.server.entity.RunwayEntity;
import com.flightcoordinator.server.entity.VehicleEntity;
import com.flightcoordinator.server.enums.AirportType;
import com.flightcoordinator.server.enums.GroundVehicleAvailability;
import com.flightcoordinator.server.enums.GroundVehicleType;
import com.flightcoordinator.server.enums.RunwaySurfaceType;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.repository.CertificationRepository;
import com.flightcoordinator.server.repository.CrewRepository;
import com.flightcoordinator.server.repository.FlightRepository;
import com.flightcoordinator.server.repository.PlaneRepository;
import com.flightcoordinator.server.repository.RouteRepository;
import com.flightcoordinator.server.repository.RunwayRepository;
import com.flightcoordinator.server.repository.VehicleRepository;

@Service
public class SampleDataGenerator {
  @Autowired
  private AirportRepository airportRepository;

  @Autowired
  private CertificationRepository certificationRepository;

  @Autowired
  private CrewRepository crewRepository;

  @Autowired
  private FlightRepository flightRepository;

  @Autowired
  private PlaneRepository planeRepository;

  @Autowired
  private RouteRepository routeRepository;

  @Autowired
  private RunwayRepository runwayRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  private final Random randomValue = new Random();

  public SampleDataGenerator generateSampleAirports() {
    for (int i = 0; i < 20; i++) {
      AirportEntity airport = new AirportEntity();
      airport.setName(SampleDataValues.sampleAirportNames.get(i));
      airport.setIataCode(SampleDataValues.sampleAirportIATACodes.get(i));
      airport.setIcaoCode(SampleDataValues.sampleAirportICAOCodes.get(i));
      airport.setCountryCode(SampleDataValues.sampleAirportCountryCodes.get(i));
      airport.setType(AirportType.INTERNATIONAL);
      airportRepository.save(airport);
    }
    return this;
  }

  public SampleDataGenerator generateSampleCertifications() {
    List<CrewEntity> allCrewEntities = crewRepository.findAll();

    for (int i = 0; i < 10; i++) {
      CertificationEntity certification = new CertificationEntity();
      certification.setName(SampleDataValues.sampleCertificationNames.get(i));
      certification.setCertificationNumber(randomValue.nextInt(100000000, 999999999));
      certification.setIssuer(SampleDataValues.sampleCertificationIssuers.get(i));
      certification.setIssuingCountry(SampleDataValues.sampleCertificationIssuingCountries.get(i));
      certification.setExpirationDate(new Date());
      certification.setValidityPeriod(12);
      certification.setAssignableRole(SampleDataValues.sampleCertificationAssignableRoles.get(i));
      certification.setDescription(SampleDataValues.sampleCertificationDescriptions.get(i));
      certification.setAssignedCrewMember(allCrewEntities.get(i));
      certificationRepository.save(certification);
    }
    return this;
  }

  public SampleDataGenerator generateSampleCrewMembers() {
    List<CertificationEntity> allCertifications = certificationRepository.findAll();
    List<AirportEntity> allAirports = airportRepository.findAll();

    for (int i = 0; i < 10; i++) {
      CrewEntity crewMember = new CrewEntity();
      crewMember.setFullName(SampleDataValues.sampleCrewMemberFullNames.get(i));
      crewMember.setEmail(SampleDataValues.sampleCrewMemberEmails.get(i));
      crewMember.setPhoneNumber((long) 1111111111);
      crewMember.setRole(SampleDataValues.sampleCrewMemberRoles.get(i));
      crewMember.setCertifications(
          allCertifications.stream()
              .filter(certification -> certification.getAssignableRole().equals(crewMember.getRole()))
              .collect(Collectors.toList()));
      crewMember.setTotalFlightHours(randomValue.nextInt(50000));
      crewMember.setBaseAirport(allAirports.get((allAirports.size() - 1) - i));
      crewMember.setAvailability(SampleDataValues.sampleCrewMemberAvailabilities.get(i));
      crewRepository.save(crewMember);
    }
    return this;
  }

  public SampleDataGenerator generateSampleRoutes() {
    List<AirportEntity> allAirports = airportRepository.findAll();

    for (int i = 0; i < 10; i++) {
      RouteEntity route = new RouteEntity();
      route.setOriginAirport(allAirports.get(i));
      route.setDestinationAirport(allAirports.get((allAirports.size() - 1) - i));
      route.setDistance(randomValue.nextFloat(15332));
      route.setEstimatedTime(randomValue.nextFloat((float) 18.83));
      routeRepository.save(route);
    }
    return this;
  }

  public SampleDataGenerator generateSampleFlights() {
    List<RouteEntity> allRoutes = routeRepository.findAll();

    for (int i = 0; i < 10; i++) {
      FlightEntity flight = new FlightEntity();
      flight.setPassengerCount(500);
      flight.setFlightRoute(allRoutes.get((allRoutes.size() - 1) - randomValue.nextInt(allRoutes.size() - 1)));
      flightRepository.save(flight);
    }
    return this;
  }

  public SampleDataGenerator generateSamplePlanes() {
    List<AirportEntity> allAirports = airportRepository.findAll();

    for (int i = 0; i < 10; i++) {
      PlaneEntity plane = new PlaneEntity();
      plane.setModel(SampleDataValues.samplePlaneModels.get(i));
      plane.setRegistrationNumber(SampleDataValues.samplePlaneRegistrationNumbers.get(i));
      plane.setPassengerCapacity(SampleDataValues.samplePlanePassengerCapacities.get(i));
      plane.setFuelEfficiency(SampleDataValues.samplePlaneFuelEfficiencies.get(i));
      plane.setMaxFlightRange(SampleDataValues.samplePlaneMaxFlightRanges.get(i));
      plane.setLastMaintenance(new Date());
      plane.setTotalFlightHours(randomValue.nextFloat(8000));
      plane.setMaxTakeoffWeight(SampleDataValues.samplePlaneMaxTakeoffWeights.get(i));
      plane.setShortestRunwayLengthRequired(SampleDataValues.samplePlaneShortestRunwayLengths.get(i));
      plane.setShortestRunwayWidthRequired(SampleDataValues.samplePlaneShortestRunwayWidths.get(i));
      plane.setPlaneStatus(SampleDataValues.samplePlaneStatuses.get(i));
      plane.setCurrentLocation(allAirports.get((allAirports.size() - 1) - randomValue.nextInt(allAirports.size() - 1)));
      plane.setAircraftOperator(SampleDataValues.sampleAircraftOperators.get(i));
      planeRepository.save(plane);
    }
    return this;
  }

  public SampleDataGenerator generateSampleRunways() {
    List<AirportEntity> allAirports = airportRepository.findAll();

    for (int i = 0; i < 10; i++) {
      RunwayEntity runway = new RunwayEntity();
      runway.setLength((float) 300);
      runway.setWidth((float) 150);
      runway.setSurfaceType(RunwaySurfaceType.ASPHALT);
      runway.setMaxWeightCapacity((float) 1500);
      runway.setOrientation(SampleDataValues.sampleRunwayOrientations.get(i));
      runway.setAirport(allAirports.get((allAirports.size() - 1) - randomValue.nextInt(allAirports.size() - 1)));
      runwayRepository.save(runway);
    }
    return this;
  }

  public SampleDataGenerator generateSampleVehicles() {
    List<AirportEntity> allAirports = airportRepository.findAll();

    for (int i = 0; i < 10; i++) {
      VehicleEntity vehicle = new VehicleEntity();
      vehicle.setType(GroundVehicleType.DE_ICER);
      vehicle.setVehicleCode(SampleDataValues.sampleGroundVehicleCodes.get(i));
      vehicle.setCapacity((float) 500);
      vehicle.setAvailability(GroundVehicleAvailability.AVAILABLE);
      vehicle.setMaintenanceDue(new Date());
      vehicle.setAirport(allAirports.get((allAirports.size() - 1) - randomValue.nextInt(allAirports.size() - 1)));
      vehicleRepository.save(vehicle);
    }
    return this;
  }
}
