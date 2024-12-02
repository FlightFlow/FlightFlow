from enum import Enum


class GroundVehicleTypes(str, Enum):
    AVAILABLE = ("Available",)
    IN_USE = ("In-Use",)
    UNDER_MAINTENANCE = ("Under-Maintenance",)
    OUT_OF_SERVICE = ("Out-Of-Service",)
