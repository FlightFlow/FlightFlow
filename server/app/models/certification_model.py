from pydantic import Field, BaseModel, ConfigDict
from typing import Optional, List

from app.database import PyObjectId
from app.models.enums.cert_issuers import CertIssuers, CertIssuingCountries


class CertificationModel(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id", default=None)
    name: str = Field(...)
    issuer: CertIssuers = Field(...)
    issuing_country: CertIssuingCountries = Field(...)
    issue_date: str = Field(...)
    expiration_date: str = Field(...)
    validity_period: str = Field(...)
    assignable_roles: List[str] = Field(...)
    description: str = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "Commercial Pilot License",
                "issuer": "FAA",
                "issuing_country": "US",
                "issue_date": "15-01-2025",
                "expiration_date": "15-01-2025",
                "validity_period": "24",
                "assignable_roles": "[CAPTAIN]",
                "description": "Allows pilots to operate for pay.",
            }
        },
    )


class CertificationUpdateModel(BaseModel):
    name: Optional[str] = Field(...)
    issuer: Optional[CertIssuers] = Field(...)
    issuing_country: Optional[CertIssuingCountries] = Field(...)
    issue_date: Optional[str] = Field(...)
    expiration_date: Optional[str] = Field(...)
    validity_period: Optional[str] = Field(...)
    assignable_roles: Optional[List[str]] = Field(...)
    description: Optional[str] = Field(...)
    model_config = ConfigDict(
        populate_by_name=True,
        arbitrary_types_allowed=True,
        json_schema_extra={
            "example": {
                "name": "Commercial Pilot License",
                "issuer": "FAA",
                "issuing_country": "US",
                "issue_date": "15-01-2025",
                "expiration_date": "15-01-2025",
                "validity_period": "24",
                "assignable_roles": "[CAPTAIN]",
                "description": "Allows pilots to operate for pay.",
            }
        },
    )


class CertificationCollection(BaseModel):
    certifications: List[CertificationModel]
