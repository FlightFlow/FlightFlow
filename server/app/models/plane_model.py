from pydantic import Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId
from app.models.enums.availabilities import PlaneAvailability


class PlaneModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    model: str = Field(...)
    registration_number: str = Field(...)
    passenger_capacity: str = Field(...)
    fuel_efficiency: int = Field(...)
    max_flight_range: int = Field(...)
    last_maintenance: str = Field(...)
    current_flight_hours: int = Field(...)
    max_takeoff_weight: int = Field(...)
    plane_status: PlaneAvailability = Field(...)
    current_location: str = Field(...)
    aircraft_operator: str = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "model": "Boeing 737-800",
                "registration_number": "N737BX",
                "passenger_capacity": "189",
                "fuel_efficiency": "15",
                "max_flight_range": "5435",
                "last_maintenance": "2024-10-15T00:00:00",
                "current_flight_hours": "12450",
                "max_takeoff_weight": "70535",
                "plane_status": "[AVAILABLE]",
                "current_location": "IST",
                "aircraft_operator": "Turkish Airlines",
            }
        },
    )


class PlaneUpdateModel(BaseModel):
    model: Optional[str] = None
    registration_number: Optional[str] = None
    passenger_capacity: Optional[str] = None
    fuel_efficiency: Optional[int] = None
    max_flight_range: Optional[int] = None
    last_maintenance: Optional[int] = None
    current_flight_hours: Optional[int] = None
    max_takeoff_weight: Optional[int] = None
    plane_status: Optional[PlaneAvailability] = None
    current_location: Optional[str] = None
    aircraft_operator: Optional[str] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "model": "Boeing 737-800",
                "registration_number": "N737BX",
                "passenger_capacity": "189",
                "fuel_efficiency": "15",
                "maximum_flight_range": "5435",
                "last_maintenance": "2024-10-15T00:00:00",
                "current_flight_hours": "12450",
                "max_takeoff_weight": "70535",
                "plane_status": "AVAILABLE",
                "current_location": "IST",
                "aircraft_operator": "Turkish Airlines",
            }
        },
    )


class PlaneCollection(BaseModel):
    Plane: List[PlaneModel]
