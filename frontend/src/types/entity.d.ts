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
    type: Enums.AirportTypes;
    runways: string[];
  }
  export interface AlgorithmResultEntity extends BaseEntity {
    routeId: string;
    planeId: string;
    crewMemberIds: string[];
    runwayId: string;
    groundVehicleIds: string[];
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
    resultId: string;
  }
  export interface CertificationEntity extends BaseEntity {
    name: string;
    issuer: Enums.CertificationIssuers;
    issuingCountry: Enums.CertificationIssuingCountry;
    expirationDate: Date;
    validityPeriod: number;
    assignableRole: string[];
    description: string;
  }
  export interface CrewEntity extends BaseEntity {
    fullName: string;
    email: string;
    phoneNumber: number;
    role: Enums.CrewRoles;
    certifications: string[];
    totalFlightHours: number;
    baseAirport: string;
    availability: CrewAvailability;
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
    currentLocation: string;
    aircraftOperator: string;
  }
  export interface RouteEntity extends BaseEntity {
    originAirportId: string;
    destinationAirportId: string;
    distance: number;
    estimatedTime: number;
  }
  export interface RunwayEntity extends BaseEntity {
    length: number;
    width: number;
    surfaceType: Enums.RunwaySurfaceTypes;
    maxWeightCapacity: number;
    orientation: string;
  }
  export interface VehicleEntity extends BaseEntity {
    type: Enums.GroundVehicleTypes;
    vehicleCode: string;
    capacity: number;
    availability: Enums.GroundVehicleAvailability;
    maintenanceDue: Date;
  }
}
export default EntityTypes;
