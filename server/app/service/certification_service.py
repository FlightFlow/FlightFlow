from app.repository.certification_repository import CertificationsRepository
from app.models.certification_model import (
    CertificationModel,
    CertificationUpdateModel,
    CertificationCollection,
)
from app.helpers.error_helper import AppError
from app.helpers import status_helper


class CertificationService:
    def __init__(self, repository: CertificationsRepository) -> None:
        self.certification_repository = repository

    async def create_certification(
        self, certification_model: CertificationModel
    ) -> CertificationModel:
        does_certification_exists = (
            await self.certification_repository.get_certification(
                certification_id=certification_model.id
            )
        )
        if does_certification_exists:
            raise AppError(
                status_code=status_helper.STATUS_CODE_CONFLICT,
                message="Certification already exists",
            )
        create_certification = await self.certification_repository.create_certification(
            certification=certification_model
        )
        is_certification_created = (
            await self.certification_repository.get_certification(
                certification_id=create_certification.id
            )
        )
        if not create_certification or not is_certification_created:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )
        return is_certification_created

    async def update_certification(
        self, certification_id: str, updated_data: CertificationUpdateModel
    ) -> None:
        does_certification_exists = (
            await self.certification_repository.get_certification(
                certification_id=certification_id
            )
        )
        if not does_certification_exists:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message=f"Cannot found certification with id {certification_id}",
            )
        certification = {
            key: value
            for key, value in updated_data.model_dump(by_alias=True).items()
            if value is not None
        }
        if len(certification) >= 1:
            update_certification = (
                await self.certification_repository.update_certification(
                    certification_id=certification_id, updated_data=updated_data
                )
            )
            if update_certification is not None:
                return
            else:
                raise AppError(
                    status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                    message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
                )
        return

    async def delete_certification(self, certification_id: str) -> None:
        does_certification_exists = (
            await self.certification_repository.get_certification(
                certification_id=certification_id
            )
        )
        if not does_certification_exists:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message=f"Cannot found certification with id {certification_id}",
            )
        delete_certification = await self.certification_repository.delete_certification(
            certification_id=certification_id
        )
        if delete_certification is not True:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )
        return

    async def get_certification(self, certification_id: str) -> CertificationCollection:
        certification = await self.certification_repository.get_certification(
            certification_id=certification_id
        )
        if certification is None:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message="Cannot find certification",
            )
        return CertificationCollection(certifications=[certification])

    async def get_all_certifications(self) -> CertificationCollection:
        certifications = await self.certification_repository.get_all_certifications()
        if certifications is None or certifications is []:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message="Cannot find any certification",
            )
        return CertificationCollection(certifications=certifications)
