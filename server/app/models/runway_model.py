from pydantic import Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId


class RunwayModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    airport_id: PyObjectId = Field(...)
    length: int = Field(...)
    width: int = Field(...)
    surface_type: str = Field(...)
    max_weight_capacity: int = Field(...)
    orientation: str = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "airport_id": "id",
                "length": "3750",
                "width": "80",
                "surface_type": "ASPHALT",
                "max_weight_capacity": "80000",
                "orientation": "16L/34R",
                "has_lighting": "True",
            }
        },
    )


class RunwayUpdateModel(BaseModel):
    airport_id: Optional[PyObjectId] = None
    length: Optional[int] = None
    width: Optional[int] = None
    surface_type: Optional[str] = None
    max_weight_capacity: Optional[int] = None
    orientation: Optional[str] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "airport_id": "id",
                "length": "3750",
                "width": "80",
                "surface_type": "ASPHALT",
                "max_weight_capacity": "80000",
                "orientation": "16L/34R",
                "has_lighting": "True",
            }
        },
    )


class RunwayCollection(BaseModel):
    runway: List[RunwayModel]
