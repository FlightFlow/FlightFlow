import { BaseModel } from "./BaseModel";

export enum AirportTypes {
  INTERNATIONAL = "International",
  REGIONAL = "Regional",
  DOMESTIC = "Domestic",
}

export interface AirportModel extends BaseModel {
  name: string;
  iataCode: string;
  icaoCode: string;
  countryCode: string;
  type: AirportTypes;
  runways: string[];
}
