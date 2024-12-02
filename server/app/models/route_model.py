from pydantic import Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId


class RoutesModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    origin_airport_id: PyObjectId = Field(...)
    destination_airport_id: PyObjectId = Field(...)
    distance: int = Field(...)
    estimated_time: int = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "origin_airport_id": "id",
                "destination_airport_id": "id",
                "distance": "1200",
                "estimated_hours": "180",
            }
        },
    )


class RoutesUpdateModel(BaseModel):
    origin_airport_id: Optional[PyObjectId] = None
    destination_airport_id: Optional[PyObjectId] = None
    distance: Optional[int] = None
    estimated_time: Optional[int] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "origin_airport_id": "id",
                "destination_airport_id": "id",
                "distance": "1200",
                "estimated_hours": "180",
            }
        },
    )


class RoutesCollection(BaseModel):
    routes: List[RoutesModel]
