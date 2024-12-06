import { BaseModel } from "./BaseModel";

export enum CrewAvailability {
  AVAILABLE = "Available",
  ON_DUTY = "On-Duty",
  ON_LEAVE = "On-Leave",
  UNAVAILABLE = "Unavailable",
}

export enum CrewRoles {
  CAPTAIN = "Captain",
  FIRST_OFFICER = "First Officer",
  SECOND_OFFICER = "Seconds Officer",
  THIRD_OFFICER = "Third Officer",
  RELIEF_CREW_MEMBER = "Relief Crew Member",
  FLIGHT_ENGINEER = "Flight Engineer",
  AIRBORNE_SENSOR_OPR = "Airborne Sensor Opr.",
  PURSER = "Purser",
  FLIGHT_ATTENDANT = "Flight Attendant",
  FLIGHT_MEDIC = "Flight Medic",
  LOADMASTER = "Loadmaster",
}

export interface CrewModel extends BaseModel {
  fullName: string;
  email: string;
  phoneNumber: number;
  role: CrewRoles;
  certifications: string[];
  totalFlightHours: number;
  baseAirport: string;
  availability: CrewAvailability;
}
