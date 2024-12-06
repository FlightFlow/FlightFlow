import { BaseModel } from "./BaseModel";

export enum PlaneAvailability {
  AVAILABLE = "Available",
  UNDER_MAINTENANCE = "Under-Maintenance",
  IS_USE = "In-Use",
  RETIRED = "Retired",
}

export interface PlaneModel extends BaseModel {
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
  planeStatus: PlaneAvailability;
  currentLocation: string;
  aircraftOperator: string;
}
