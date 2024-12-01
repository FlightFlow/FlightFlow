import os
from dotenv import load_dotenv

from app.logger import logger
from app.helpers.error_helper import AppError
from app.helpers import status_helper

load_dotenv()


class AppConfig:
    project_name = "FlightCoordinator"
    project_desc = (
        "Automated flight manager app for final year computer engineering project"
    )
    project_version = "v0.0.1"
    app_settings = {
        "app_env": os.getenv("APP_ENV"),
        "app_port": os.getenv("APP_PORT"),
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
                raise AppError(
                    status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                    message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
                )
            if key is "app_env" and value not in ["dev", "test", "prod"]:
                logger.error(
                    f"Error ocurred while parsing '.env' file: {key} should be 'dev', 'test', or 'prod'"
                )
                raise AppError(
                    status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                    message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
                )
