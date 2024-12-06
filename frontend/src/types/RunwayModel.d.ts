import { BaseModel } from "./BaseModel";

export enum RunwaySurfaceTypes {
  ASPHALT = "Asphalt",
  CONCRETE = "Concrete",
  GRASS = "Grass",
  GRAVEL = "Gravel",
  DIRT = "Dirt",
  COMP_MATERIAL = "Composite Materials",
}

export interface RunwayModel extends BaseModel {
  length: number;
  width: number;
  surfaceType: RunwaySurfaceTypes;
  maxWeightCapacity: number;
  orientation: string;
}
