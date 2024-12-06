import { BaseModel } from "./BaseModel";

export interface RouteModel extends BaseModel {
  originAirportId: string;
  destinationAirportId: string;
  distance: number;
  estimatedTime: number;
}
