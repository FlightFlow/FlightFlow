package com.flightcoordinator.server.dev;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.entity.AirportEntity;
import com.flightcoordinator.server.entity.CertificationEntity;
import com.flightcoordinator.server.entity.CrewEntity;
import com.flightcoordinator.server.entity.FlightEntity;
import com.flightcoordinator.server.entity.PlaneEntity;
import com.flightcoordinator.server.entity.RouteEntity;
import com.flightcoordinator.server.entity.RunwayEntity;
import com.flightcoordinator.server.entity.VehicleEntity;
import com.flightcoordinator.server.enums.AirportType;
import com.flightcoordinator.server.enums.CertificationIssuer;
import com.flightcoordinator.server.enums.CertificationIssuingCountry;
import com.flightcoordinator.server.enums.CrewAvailability;
import com.flightcoordinator.server.enums.CrewRole;
import com.flightcoordinator.server.enums.GroundVehicleAvailability;
import com.flightcoordinator.server.enums.GroundVehicleType;
import com.flightcoordinator.server.enums.PlaneAvailability;
import com.flightcoordinator.server.enums.RunwaySurfaceType;
import com.flightcoordinator.server.repository.AirportRepository;
import com.flightcoordinator.server.repository.CertificationRepository;
import com.flightcoordinator.server.repository.CrewRepository;
import com.flightcoordinator.server.repository.FlightRepository;
import com.flightcoordinator.server.repository.PlaneRepository;
import com.flightcoordinator.server.repository.RouteRepository;
import com.flightcoordinator.server.repository.RunwayRepository;
import com.flightcoordinator.server.repository.VehicleRepository;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/${server.api-version}/sampledata")
@Tag(name = "Test Data Controller", description = "Endpoint for creating sample data for development.")
public class SampleDataController {
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

  @PostMapping("/create")
  @Operation(summary = "Create sample data for development", description = "Create sample data for development.")
  public ResponseEntity<ResponseObject<List<Object>>> createTestData() {
    /*
     * This controller is for testing purposes and does not follow layered
     * architecture patterns.
     */

    AirportEntity airport1 = new AirportEntity();
    airport1.setName("Sample Airport 1");
    airport1.setIataCode("SAO");
    airport1.setIcaoCode("SIA1");
    airport1.setCountryCode("US");
    airport1.setType(AirportType.INTERNATIONAL);
    airportRepository.save(airport1);

    AirportEntity airport2 = new AirportEntity();
    airport2.setName("Sample Airport 2");
    airport2.setIataCode("SAT");
    airport2.setIcaoCode("SIA2");
    airport2.setCountryCode("US");
    airport2.setType(AirportType.INTERNATIONAL);
    airportRepository.save(airport2);

    CertificationEntity certification = new CertificationEntity();
    certification.setName("Sample Certification");
    certification.setIssuer(CertificationIssuer.BOEING);
    certification.setIssuingCountry(CertificationIssuingCountry.US);
    certification.setExpirationDate(new Date());
    certification.setValidityPeriod(12);
    certification.setAssignableRoles(Arrays.asList(CrewRole.CAPTAIN));
    certification.setDescription("This is a sample description.");
    certificationRepository.save(certification);

    CrewEntity crewMember = new CrewEntity();
    crewMember.setFullName("John Doe");
    crewMember.setEmail("johndoe@example.com");
    crewMember.setPhoneNumber(1111111111);
    crewMember.setRole(CrewRole.CAPTAIN);
    crewMember.setCertifications(Arrays.asList(certification));
    crewMember.setTotalFlightHours(0);
    crewMember.setBaseAirport(airport1);
    crewMember.setAvailability(CrewAvailability.AVAILABLE);
    crewRepository.save(crewMember);

    RouteEntity route = new RouteEntity();
    route.setOriginAirport(airport1);
    route.setDestinationAirport(airport2);
    route.setDistance((float) 500);
    route.setEstimatedTime((float) 8);
    routeRepository.save(route);

    FlightEntity flight = new FlightEntity();
    flight.setPassengerCount(500);
    flight.setFlightRoute(route);
    flightRepository.save(flight);

    PlaneEntity plane = new PlaneEntity();
    plane.setModel("Test Plane");
    plane.setRegistrationNumber("TP001");
    plane.setPassengerCapacity(500);
    plane.setFuelEfficiency((float) 1);
    plane.setMaxFlightRange((float) 1000);
    plane.setLastMaintenance(new Date());
    plane.setTotalFlightHours((float) 150);
    plane.setMaxTakeoffWeight((float) 1000);
    plane.setShortestRunwayLengthRequired((float) 250);
    plane.setShortestRunwayWidthRequired((float) 100);
    plane.setPlaneStatus(PlaneAvailability.AVAILABLE);
    plane.setCurrentLocation(airport1);
    plane.setAircraftOperator("John Doe Co.");
    planeRepository.save(plane);

    RunwayEntity runway = new RunwayEntity();
    runway.setLength((float) 300);
    runway.setWidth((float) 150);
    runway.setSurfaceType(RunwaySurfaceType.ASPHALT);
    runway.setMaxWeightCapacity((float) 1500);
    runway.setOrientation("uhh");
    runway.setAirport(airport1);
    runwayRepository.save(runway);

    VehicleEntity vehicle = new VehicleEntity();
    vehicle.setType(GroundVehicleType.DE_ICER);
    vehicle.setVehicleCode("DIGV001");
    vehicle.setCapacity((float) 500);
    vehicle.setAvailability(GroundVehicleAvailability.AVAILABLE);
    vehicle.setMaintenanceDue(new Date());
    vehicle.setAirport(airport2);
    vehicleRepository.save(vehicle);

    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, "controllers.createResponse", null);
  }
}
