# from pydantic import Field, BaseModel, ConfigDict
# from typing import Optional, List

# from app.database import PyObjectId


# class PlaneModel(BaseModel):
#     id: Optional[PyObjectId] = Field(alias="_id", default=None)
#     model: str = Field(...)
#     passenger_capacity: str = Field(...)
#     fuel_efficiency: int = Field(...)
#     maintenance_due: str = Field(...)
#     status: Availibility = Field(...)
#     model_config = ConfigDict(
#         populate_by_name=True,
#         arbitrary_types_allowed=True,
#         json_schema_extra={
#             "example": {
#                 "name": "John Doe",
#                 "email": "johndoe@example.com",
#                 "role": "CAPTAIN",
#                 "certifications": "[]",
#                 "availability": "ON_DUTY",
#             }
#         },
#     )


# class PlaneUpdateModel(BaseModel):
#     name: Optional[str] = None
#     email: Optional[EmailStr] = None
#     role: Optional[Roles] = None
#     certifications: Optional[List[PyObjectId]] = None
#     availability: Optional[Availibility] = None
#     model_config = ConfigDict(
#         populate_by_name=True,
#         arbitrary_types_allowed=True,
#         json_schema_extra={
#             "example": {
#                 "name": "John Doe",
#                 "email": "johndoe@example.com",
#                 "role": "CAPTAIN",
#                 "certifications": "[]",
#                 "availability": "ON_DUTY",
#             }
#         },
#     )


# class PlaneCollection(BaseModel):
#     Plane: List[PlaneModel]
