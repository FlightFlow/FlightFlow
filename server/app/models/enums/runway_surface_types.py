from enum import Enum


class RunwaySurfaceTypes(str, Enum):
    ASPHALT = "Asphalt"
    CONCRETE = "Concrete"
    GRASS = "Grass"
    GRAVEL = "Gravel"
    DIRT = "Dirt"
    COMP_MATERIAL = "Composite Materials"
