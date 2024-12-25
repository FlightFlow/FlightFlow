namespace DataTransfer {
  export interface AirportDTO {
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
  export interface AlgorithmResultDTO {
    flightId: string;
    planeId: string;
    crewMemberIds: List<string>;
    takeoffRunwayId: string;
    landingRunwayId: string;
    originAirportGroundVehicleIds: List<string>;
    destinationAirportGroundVehicleIds: List<string>;
  }
  export interface AlgorithmRunDTO {
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
  export interface CertificationDTO {
    name: string;
    issuer: Enums.CertificationIssuer;
    issuingCountry: Enums.CertificationIssuingCountry;
    expirationDate: Date;
    validityPeriod: number;
    assignableRole: Enums.CrewRoles[];
    description: string;
  }
  export interface CrewDTO {
    fullName: string;
    email: string;
    phoneNumber: number;
    role: Enums.CrewRoles;
    certificationIds: List<string>;
    totalFlightHours: number;
    baseAirportId: string;
    availability: CrewAvailability;
  }
  export interface FlightDTO {
    passengerCount: number;
    flightRouteId: string;
  }
  export interface PlaneDTO {
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
  export interface RouteDTO {
    originAirportId: string;
    destinationAirportId: string;
    distance: number;
    estimatedTime: number;
  }
  export interface RunwayDTO {
    length: number;
    width: number;
    surfaceType: Enums.RunwaySurfaceType;
    maxWeightCapacity: number;
    orientation: string;
    airportId: string;
  }
  export interface VehicleDTO {
    type: Enums.GroundVehicleType;
    vehicleCode: string;
    capacity: number;
    availability: Enums.GroundVehicleAvailability;
    maintenanceDue: Date;
  }
}
export default DataTransfer;
