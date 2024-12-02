from enum import Enum


class GroundVehicleTypes(str, Enum):
    TUG = "Tug"
    REFUELER = "Refueler"
    LOADER = "Loader"
    CATERING = "Catering"
    DE_ICER = "De-Icer"
    PUSHBACK = "Pushback"
    BUS = "Bus"
