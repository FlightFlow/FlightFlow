from motor.motor_asyncio import AsyncIOMotorCollection
from pymongo import ReturnDocument

from app.database import PyObjectId
from app.models.crew_model import CrewModel, CrewCollection
from app.helpers.error_helper import AppError
from app.helpers import status_helper


class CrewRepository:
    def __init__(self, db_collection: AsyncIOMotorCollection) -> None:
        self.db_collection = db_collection

    async def create_crew_member(
        self,
        crew_member: CrewModel,
    ) -> None | CrewModel:
        new_crew_member = await self.db_collection.insert_one(data=crew_member)
        if not new_crew_member:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )

        return new_crew_member

    async def update_crew_member(self, crew_member_id: str, updated_data) -> CrewModel:
        updated_crew_member = await self.db_collection.find_one_and_update(
            {"_id": PyObjectId(crew_member_id)},
            {"$set": updated_data},
            return_document=ReturnDocument.AFTER,
        )
        if not updated_crew_member:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )

        return updated_crew_member

    async def delete_crew_member(self, crew_member_id: str) -> bool:
        is_deleted = await self.db_collection.find_one_and_delete(
            {"_id": PyObjectId(crew_member_id)}
        )
        if not is_deleted:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )

        return True

    async def get_crew_member(self, crew_member_id: str) -> None | CrewModel:
        crew_member = await self.db_collection.find_one(
            {"_id": PyObjectId(crew_member_id)}
        )
        if crew_member is None:
            return None

        return CrewCollection(crew=[crew_member])

    async def get_all_crew_members(self) -> None | CrewCollection:
        crew_members = await self.db_collection.find().to_list()
        if len(crew_members) == 0:
            return CrewCollection(crew=[])

        return crew_members
