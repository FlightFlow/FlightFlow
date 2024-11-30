from motor.motor_asyncio import AsyncIOMotorClient
from dotenv import load_dotenv

from app.config import AppConfig
from app.utils.logger import logger

load_dotenv()
db_client: AsyncIOMotorClient = None


class Database:
    async def get_db() -> AsyncIOMotorClient:
        db_name = AppConfig.app_settings.get("db_name")
        return db_client[db_name]

    async def connect():
        global db_client
        try:
            db_client = AsyncIOMotorClient(AppConfig.app_settings.get("db_uri"))
            logger.info("Connected to MongoDB")
        except Exception as e:
            logger.error(
                f"An error ocurred while trying to connect to the database: {e}"
            )
            raise

    async def disconnect():
        global db_client
        if db_client is None:
            logger.warning("Cannot find connection, nothing to disconnect from.")
            return
        db_client.close()
        db_client = None
        logger.info("Disconnected from MongoDB")
