from app.repository.crew_repository import CrewRepository
from app.models.crew_model import CrewCollection, CrewModel, CrewUpdateModel
from app.helpers.error_helper import AppError
from app.helpers import status_helper


class CrewService:
    def __init__(self, repository: CrewRepository) -> None:
        self.crew_repository = repository

    async def create_crew_member(self, crew_member: CrewModel) -> CrewModel:
        does_crew_member_exists = await self.crew_repository.get_crew_member(
            crew_member_id=crew_member.id
        )
        if does_crew_member_exists:
            raise AppError(
                status_code=status_helper.STATUS_CODE_CONFLICT,
                message="Crew member already exists",
            )
        create_crew_member = await self.crew_repository.create_crew_member(
            crew_member=crew_member
        )
        is_crew_member_created = await self.crew_repository.get_crew_member(
            crew_member_id=create_crew_member.id
        )
        if not create_crew_member or not is_crew_member_created:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )
        return is_crew_member_created

    async def update_crew_member(
        self, crew_member_id: str, updated_data: CrewUpdateModel
    ) -> None:
        does_crew_member_exists = await self.crew_repository.get_crew_member(
            crew_member_id=crew_member_id
        )
        if not does_crew_member_exists:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message=f"Cannot found crew member with id {crew_member_id}",
            )
        crew_member = {
            key: value
            for key, value in updated_data.model_dump(by_alias=True).items()
            if value is not None
        }
        if len(crew_member) >= 1:
            update_crew_member = await self.crew_repository.update_crew_member(
                crew_member_id=crew_member_id, updated_data=updated_data
            )
            if update_crew_member is not None:
                return
            else:
                raise AppError(
                    status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                    message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
                )
        return

    async def delete_crew_member(self, crew_member_id: str) -> None:
        does_crew_member_exists = await self.crew_repository.get_crew_member(
            crew_member_id=crew_member_id
        )
        if not does_crew_member_exists:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message=f"Cannot found crew member with id {crew_member_id}",
            )
        delete_crew_member = await self.crew_repository.delete_crew_member(
            crew_member_id=crew_member_id
        )
        if delete_crew_member is not True:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )
        return

    async def get_crew_member(self, crew_member_id: str) -> CrewCollection:
        crew_member = await self.crew_repository.get_crew_member(
            crew_member_id=crew_member_id
        )
        if crew_member is None:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message="Cannot find crew member",
            )
        return CrewCollection(crew=crew_member)

    async def get_all_crew_members(self) -> CrewCollection:
        crew_members = await self.crew_repository.get_all_crew_members()
        if crew_members is None or crew_members is []:
            raise AppError(
                status_code=status_helper.STATUS_CODE_NOT_FOUND,
                message="Cannot find any crew member",
            )
        return CrewCollection(crew=crew_members)
