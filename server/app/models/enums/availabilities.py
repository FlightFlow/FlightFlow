from enum import Enum


class CrewAvailibility(str, Enum):
    AVAILABLE = "Available"
    ON_DUTY = "On Duty"
    ON_LEAVE = "On Leave"
    UNAVAILABLE = "Unavailable"


class GroundVehicleAvailability(str, Enum):
    AVAILABLE = "Available"
    IN_USE = "In-Use"
    UNDER_MAINTENANCE = "Under-Maintenance"
    OUT_OF_SERVICE = "Out-Of-Service"


class PlaneAvailability(str, Enum):
    AVAILABLE = "Available"
    UNDER_MAINTENANCE = "Under Maintenance"
    IS_USE = "In-Use"
    RETIRED = "Retired"
