from enum import Enum


class Roles(str, Enum):
    AVAILABLE = "Available"
    UNDER_MAINTENANCE = "Under Maintenance"
    IS_USE = "In-Use"
    RETIRED = "Retired"
