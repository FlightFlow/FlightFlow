from enum import Enum


class FlightStatus(str, Enum):
    SCHEDULED = "Scheduled"
    IN_PROGRESS = "In-Progress"
    COMPLETED = "Completed"
    CANCELLED = "Cancelled"
    DELAYED = "Delayed"
