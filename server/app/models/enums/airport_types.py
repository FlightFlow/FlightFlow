from enum import Enum


class AirportTypes(str, Enum):
    INTERNATIONAL = "International"
    REGIONAL = "Regional"
    DOMESTIC = "Domestic"
