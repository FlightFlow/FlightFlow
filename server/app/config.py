import os
from dotenv import load_dotenv

from app.utils.logger import logger

load_dotenv()


class AppConfig:
    project_name = "FlightCoordinator"
    project_version = "v0.0.1"
    app_settings = {
        "app_port": os.getenv("PORT"),
        "db_name": os.getenv("DB_NAME"),
        "db_uri": os.getenv("DB_URI"),
        "frontend_url": os.getenv("FRONTEND_URL"),
    }

    @classmethod
    def validate_config(cls) -> None:
        for key, value in cls.app_settings.items():
            if None is value:
                logger.error(
                    f"Error ocurred while parsing '.env' file: {key} cannot be None"
                )
                exit(1)
