from enum import Enum


class CrewAvailibility(str, Enum):
    AVAILABLE = "Available"
    ON_DUTY = "On Duty"
    ON_LEAVE = "On Leave"
    UNAVAILABLE = "Unavailable"
