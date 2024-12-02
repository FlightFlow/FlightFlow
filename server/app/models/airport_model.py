from pydantic import Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId
from app.models.enums.airport_types import AirportTypes


class AirportModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    name: str = Field(...)
    iata_code: str = Field(...)
    icao_code: str = Field(...)
    country: str = Field(...)
    type: AirportTypes = Field(...)
    runways: List[str] = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "Istanbul Airport",
                "iata_code": "IST",
                "icao_code": "LTFM",
                "country": "TR",
                "type": "INTERNATIONAL",
                "runways": "[id_1, id_2]",
            }
        },
    )


class AirportUpdateModel(BaseModel):
    name: Optional[str] = None
    iata_code: Optional[str] = None
    icao_code: Optional[str] = None
    country: Optional[str] = None
    type: Optional[AirportTypes] = None
    runways: Optional[List[str]] = None
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "Istanbul Airport",
                "iata_code": "IST",
                "icao_code": "LTFM",
                "country": "TR",
                "type": "INTERNATIONAL",
                "runways": "[id_1, id_2]",
            }
        },
    )


class AirportCollection(BaseModel):
    airports: List[AirportModel]
