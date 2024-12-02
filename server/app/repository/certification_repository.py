from motor.motor_asyncio import AsyncIOMotorCollection
from pymongo import ReturnDocument

from app.database import PyObjectId
from app.models.certification_model import (
    CertificationCollection,
    CertificationModel,
)
from app.helpers.error_helper import AppError
from app.helpers import status_helper


class CertificationsRepository:
    def __init__(self, db_collection: AsyncIOMotorCollection) -> None:
        self.db_collection = db_collection

    async def create_certification(
        self, certification: CertificationModel
    ) -> None | CertificationModel:
        new_certification = await self.db_collection.insert_one(data=certification)
        if not new_certification:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )

        return certification

    async def update_certification(
        self, certification_id: str, updated_data
    ) -> CertificationModel:
        updated_certification = await self.db_collection.find_one_and_update(
            {"_id": PyObjectId(certification_id)},
            {"$set": updated_data},
            return_document=ReturnDocument.AFTER,
        )
        if not updated_certification:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )

        return updated_certification

    async def delete_certification(self, certification_id: str) -> bool:
        is_deleted = await self.db_collection.find_one_and_delete(
            {"_id": PyObjectId(certification_id)}
        )
        if not is_deleted:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )

        return True

    async def get_certification(
        self, certification_id: str
    ) -> None | CertificationCollection:
        certification = await self.db_collection.find_one(
            {"_id": PyObjectId(certification_id)}
        )
        if certification is None:
            return CertificationCollection(certifications=[])

        return CertificationCollection(certifications=[certification])

    async def get_all_certifications(self) -> None | CertificationCollection:
        certifications = await self.db_collection.find().to_list()
        if len(certifications) == 0:
            return CertificationCollection(certifications=[])

        return CertificationCollection(certifications=certifications)
