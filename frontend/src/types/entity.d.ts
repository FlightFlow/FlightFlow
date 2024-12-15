import Enums from "./enums";

namespace EntityTypes {
  export interface BaseEntity {
    id: string;
  }
  export interface AirportEntity extends BaseEntity {
    name: string;
    iataCode: string;
    icaoCode: string;
    countryCode: string;
    type: Enums.AirportType;
    runways: List<RunwayEntity>;
  }
  export interface AlgorithmResultEntity extends BaseEntity {
    flight: FlightEntity;
    plane: PlaneEntity;
    crewMembers: List<CrewEntity>;
    takeoffRunway: RunwayEntity;
    landingRunway: RunwayEntity;
    originAirportGroundVehicles: List<VehicleEntity>;
    destinationAirportGroundVehicles: List<VehicleEntity>;
  }
  export interface AlgorithmRunEntity extends BaseEntity {
    algorithmName: string;
    startTime: Date;
    endTime: Date;
    runtimeInMilliseconds: number;
    parameters: Map<string, object>;
    resourcesUsed: Map<string, object>;
    constrainsMet: Map<string, boolean>;
    logs: string[];
    isSuccessful: boolean;
    failureReason: string;
    isResultsSaved: boolean;
    resultId: AlgorithmResultEntity;
  }
  export interface CertificationEntity extends BaseEntity {
    name: string;
    issuer: Enums.CertificationIssuer;
    issuingCountry: Enums.CertificationIssuingCountry;
    expirationDate: Date;
    validityPeriod: number;
    assignableRole: Enums.CrewRoles[];
    description: string;
  }
  export interface CrewEntity extends BaseEntity {
    fullName: string;
    email: string;
    phoneNumber: number;
    role: Enums.CrewRoles;
    certifications: List<CertificationEntity>;
    totalFlightHours: number;
    baseAirport: AirportEntity;
    availability: CrewAvailability;
  }
  export interface FlightEntity extends BaseEntity {
    passengerCount: number;
    flightRoute: RouteEntity;
  }
  export interface PlaneEntity extends BaseEntity {
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
    currentLocation: AirportEntity;
    aircraftOperator: string;
  }
  export interface RouteEntity extends BaseEntity {
    originAirportId: AirportEntity;
    destinationAirportId: AirportEntity;
    distance: number;
    estimatedTime: number;
  }
  export interface RunwayEntity extends BaseEntity {
    length: number;
    width: number;
    surfaceType: Enums.RunwaySurfaceType;
    maxWeightCapacity: number;
    orientation: string;
  }
  export interface SystemRoleEntity extends BaseEntity {
    roleName: string;
    permissionPerResource: Map<Enums.SystemResource, List<Enums.SystemPermission>>;
  }
  export interface UserEntity extends BaseEntity {
    fullName: string;
    email: string;
    role: SystemRoleEntity;
    isActive: boolean;
    isLocked: boolean;
  }
  export interface VehicleEntity extends BaseEntity {
    type: Enums.GroundVehicleType;
    vehicleCode: string;
    capacity: number;
    availability: Enums.GroundVehicleAvailability;
    maintenanceDue: Date;
  }
}
export default EntityTypes;
