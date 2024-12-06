import { BaseModel } from "./BaseModel";

export enum GroundVehicleAvailability {
  AVAILABLE = "Available",
  IN_USE = "In-Use",
  UNDER_MAINTENANCE = "Under-Maintenance",
  OUT_OF_SERVICE = "Out-Of-Service",
}

export enum GroundVehicleTypes {
  TUG = "Tug",
  REFUELER = "Refueler",
  LOADER = "Loader",
  CATERING = "Catering",
  DE_ICER = "De-Icer",
  PUSHBACK = "Pushback",
  BUS = "Bus",
}

export interface VehicleModel extends BaseModel {
  type: GroundVehicleTypes;
  vehicleCode: string;
  capacity: number;
  availability: GroundVehicleAvailability;
  maintenanceDue: Date;
}
