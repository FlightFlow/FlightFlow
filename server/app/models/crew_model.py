from enum import Enum
from pydantic import EmailStr, Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId


class Roles(str, Enum):
    captain = "captain"
    first_officer = "first_officer"
    second_officer = "seconds_officer"
    third_officer = "third_officer"
    relief_crew_member = "relief_crew_member"
    flight_engineer = "flight_engineer"
    airborne_sensor_opr = "airborne_sensor_opr"
    purser = "purser"
    flight_attendant = "flight_attendant"
    flight_medic = "flight_medic"
    loadmaster = "loadmaster"


class Availibility(str, Enum):
    available = "available"
    on_duty = "on_duty"
    on_leave = "on_leave"
    unavailable = "unavailable"


class CrewModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    name: str = Field(...)
    email: EmailStr = Field(...)
    role: Roles = Field(..., examples=["captain"])
    certifications: List = Field(...)
    availability: Availibility = Field(..., examples=["on_duty"])
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "John Doe",
                "email": "johndoe@example.com",
                "role": "captain",
                "certifications": "",
                "availability": "on_duty",
            }
        },
    )


class CrewUpdateModel(BaseModel):
    name: Optional[str] = None
    email: Optional[EmailStr] = None
    role: Optional[Roles] = None
    certifications: Optional[List] = None
    availability: Optional[Availibility] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "John Doe",
                "email": "johndoe@example.com",
                "role": "captain",
                "certifications": "",
                "availability": "on_duty",
            }
        },
    )


class CrewCollection(BaseModel):
    crew: List[CrewModel]
