from pydantic import Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId
from app.models.enums.availabilities import GroundVehicleAvailability
from app.models.enums.ground_vehicle_types import GroundVehicleTypes


class VehicleModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    type: GroundVehicleTypes = Field(...)
    vehicle_code: str = Field(...)
    capacity: int = Field(...)
    availability: GroundVehicleAvailability = Field(...)
    maintenance_due: str = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "type": "TUG",
                "vehicle_code": "GV-001",
                "capacity": "5000",
                "availability": "IN_SERVICE",
                "maintenance_due": "15-12-2024",
            }
        },
    )


class VehicleUpdateModel(BaseModel):
    type: Optional[GroundVehicleTypes] = None
    vehicle_code: Optional[str] = None
    capacity: Optional[int] = None
    availability: Optional[GroundVehicleAvailability] = None
    maintenance_due: Optional[str] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "type": "TUG",
                "vehicle_code": "GV-001",
                "capacity": "5000",
                "availability": "IN_SERVICE",
                "maintenance_due": "15-12-2024",
            }
        },
    )


class VehicleCollection(BaseModel):
    vehicle: List[VehicleModel]
