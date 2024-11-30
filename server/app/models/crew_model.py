from sqlalchemy import Column, Integer, String, ForeignKey, Enum
from sqlalchemy.orm import relationship
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()
Roles = (
    "captain",
    "first_officer",
    "seconds_officer",
    "third_officer",
    "relief_crew_member",
    "flight_engineer",
    "airborne_sensor_opr",
    "purser",
    "flight_attendant",
    "flight_medic",
    "loadmaster",
)
Availability = (
    "available",
    "on_duty",
    "on_leave",
    "unavailable",
)


class AircrewModel(Base):
    __tablename__ = "aircrew_table"

    id = Column(Integer, primary_key=True)
    name = Column(String, nullable=False)
    role = Column(Enum(*Roles, name="role"), nullable=False)
    certifications = Column(String, nullable=False)
    availability = Column(*Availability, nullable=False)
