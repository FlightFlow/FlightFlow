namespace DataTransfer {
  interface BaseDTO {
    id: string;
  }
  interface AirportDTO extends BaseDTO {
    name: string;
    iataCode: string;
    icaoCode: string;
    countryCode: string;
    type: Enums.AirportType;
    runwayIds: List<string>;
    vehiclesPresentIds: List<string>;
    planesPresentIds: List<string>;
    routesOriginatingFromAirportIds: List<string>;
    routesDestinedForAirportIds: List<string>;
    crewMembersPresentIds: List<string>;
  }
  interface AlgorithmResultDTO extends BaseDTO {
    flightId: string;
    planeId: string;
    crewMemberIds: List<string>;
    takeoffRunwayId: string;
    landingRunwayId: string;
    originAirportGroundVehicleIds: List<string>;
    destinationAirportGroundVehicleIds: List<string>;
  }
  interface AlgorithmRunDTO extends BaseDTO {
    algorithmName: string;
    startTime: Date;
    endTime: Date;
    runtimeInMilliseconds: number;
    parametersJson: string;
    resourcesJson: string;
    constrainsMet: Map<string, boolean>;
    logs: string[];
    isSuccessful: boolean;
    failureReason: string;
    isResultsSaved: boolean;
    resultId: string;
  }
  interface CertificationDTO extends BaseDTO {
    name: string;
    certificationNumber: string;
    issuer: Enums.CertificationIssuer;
    issuingCountry: Enums.CertificationIssuingCountry;
    expirationDate: Date;
    validityPeriod: number;
    assignableRole: Enums.CrewRoles;
    description: string;
    assignedCrewMember: string;
  }
  interface CrewDTO extends BaseDTO {
    fullName: string;
    email: string;
    phoneNumber: number;
    role: Enums.CrewRoles;
    certificationIds: List<string>;
    totalFlightHours: number;
    baseAirportId: string;
    availability: CrewAvailability;
  }
  interface FlightDTO extends BaseDTO {
    passengerCount: number;
    flightRouteId: string;
  }
  interface PlaneDTO extends BaseDTO {
    model: string;
    registrationNumber: string;
    passengerCapacity: number;
    fuelEfficiency: number;
    maxFlightRange: number;
    lastMaintenance: Date;
    totalFlightHours: number;
    maxTakeoffWeight: number;
    shortestRunwayLengthRequired: number;
    shortestRunwayWidthRequired: number;
    planeStatus: Enums.PlaneAvailability;
    currentLocationId: string;
    aircraftOperator: string;
  }
  interface RouteDTO extends BaseDTO {
    originAirportId: string;
    destinationAirportId: string;
    distance: number;
    estimatedTime: number;
  }
  interface RunwayDTO extends BaseDTO {
    length: number;
    width: number;
    surfaceType: Enums.RunwaySurfaceType;
    maxWeightCapacity: number;
    orientation: string;
    airportId: string;
  }
  interface VehicleDTO extends BaseDTO {
    type: Enums.GroundVehicleType;
    vehicleCode: string;
    capacity: number;
    availability: Enums.GroundVehicleAvailability;
    maintenanceDue: Date;
    airportId: string;
  }
}
export default DataTransfer;
