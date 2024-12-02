from pydantic import EmailStr, Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId
from app.models.enums.crew_roles import CrewRoles
from app.models.enums.availabilities import CrewAvailibility


class CrewModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    full_name: str = Field(...)
    email: EmailStr = Field(...)
    phone_number: int = Field(...)
    role: CrewRoles = Field(...)
    certifications: List[str] = Field(...)
    total_flight_hours: int = Field(...)
    base_airport: str = Field(...)
    availability: CrewAvailibility = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "full_name": "John Doe",
                "email": "johndoe@example.com",
                "phone_number": "+90 123 456 7890",
                "role": "CAPTAIN",
                "certifications": "[]",
                "total_flight_hours": "12000",
                "base_airport": "IST",
                "availability": "ON_DUTY",
            }
        },
    )


class CrewUpdateModel(BaseModel):
    full_name: Optional[str] = None
    email: Optional[EmailStr] = None
    phone_number: Optional[int] = None
    role: Optional[CrewRoles] = None
    certifications: List[str] = None
    total_flight_hours: Optional[int] = None
    base_airport: Optional[str] = None
    availability: Optional[CrewAvailibility] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "full_name": "John Doe",
                "email": "johndoe@example.com",
                "phone_number": "+90 123 456 7890",
                "role": "CAPTAIN",
                "certifications": "[]",
                "total_flight_hours": "12000",
                "base_airport": "IST",
                "availability": "ON_DUTY",
            }
        },
    )


class CrewCollection(BaseModel):
    crew: List[CrewModel]
