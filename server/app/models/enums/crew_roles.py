from enum import Enum


class CrewRoles(str, Enum):
    CAPTAIN = "Captain"
    FIRST_OFFICER = "First Officer"
    SECOND_OFFICER = "Seconds Officer"
    THIRD_OFFICER = "Third Officer"
    RELIEF_CREW_MEMBER = "Relief Crew Member"
    FLIGHT_ENGINEER = "Flight Engineer"
    AIRBORNE_SENSOR_OPR = "Airborne Sensor Opr."
    PURSER = "Purser"
    FLIGHT_ATTENDANT = "Flight Attendant"
    FLIGHT_MEDIC = "Flight Medic"
    LOADMASTER = "Loadmaster"
