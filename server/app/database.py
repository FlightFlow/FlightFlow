from dotenv import load_dotenv
from typing_extensions import Annotated
from pydantic.functional_validators import BeforeValidator
from motor.motor_asyncio import AsyncIOMotorClient, AsyncIOMotorDatabase

from app.logger import logger
from app.config import AppConfig
from app.helpers.error_helper import AppError
from app.helpers import status_helper

PyObjectId = Annotated[str, BeforeValidator(str)]

load_dotenv()


class Database:
    def __init__(self):
        self.db_name = AppConfig.app_settings.get("db_name")
        self.db_client: AsyncIOMotorClient = None
        self.database: AsyncIOMotorDatabase = None
        self.initialized: bool = False

        try:
            self.db_client = AsyncIOMotorClient(AppConfig.app_settings.get("db_uri"))
            self.database = self.db_client[self.db_name]
            self.initialized = True
            logger.info("Connected to MongoDB")

        except Exception as e:
            logger.error(
                f"An error ocurred while trying to connect to the database: {e}"
            )
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=e.__traceback__,
            )

    async def disconnect(self) -> None:
        if self.database is not None:
            if self.db_client is None:
                logger.warning("Cannot find connection, nothing to disconnect from.")
                return
            self.db_client.close()
            self.db_client = None
            self.database = None
            logger.info("Disconnected from MongoDB")
        else:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message="Database connection is not initialized, nothing to disconnect from.",
            )

    def get_collection(self, collection: str) -> AsyncIOMotorDatabase:
        if self.database is None:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message="Database connection is not initialized.",
            )

        return self.database.get_collection(name=collection)


db_instance: Database = Database()
