from pydantic import EmailStr, Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId
from app.models.enums.crew_roles import CrewRoles
from app.models.enums.crew_availability import CrewAvailibility


class CrewModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    name: str = Field(...)
    email: EmailStr = Field(...)
    role: CrewRoles = Field(...)
    certifications: List[PyObjectId] = Field(...)
    availability: CrewAvailibility = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "John Doe",
                "email": "johndoe@example.com",
                "role": "CAPTAIN",
                "certifications": "[]",
                "availability": "ON_DUTY",
            }
        },
    )


class CrewUpdateModel(BaseModel):
    name: Optional[str] = None
    email: Optional[EmailStr] = None
    role: Optional[CrewRoles] = None
    certifications: Optional[List[PyObjectId]] = None
    availability: Optional[CrewAvailibility] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "John Doe",
                "email": "johndoe@example.com",
                "role": "CAPTAIN",
                "certifications": "[]",
                "availability": "ON_DUTY",
            }
        },
    )


class CrewCollection(BaseModel):
    crew: List[CrewModel]
