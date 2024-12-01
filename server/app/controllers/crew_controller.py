from fastapi import APIRouter, Body
from fastapi.responses import JSONResponse

from app.database import db_instance
from app.models.crew_model import CrewCollection, CrewModel, CrewUpdateModel
from app.repository.crew_repository import CrewRepository
from app.service.crew_service import CrewService
from app.helpers.response_helper import ResponseHelper, ResponseObject
from app.helpers import status_helper

router: APIRouter = APIRouter(tags=["Crew Member Controller"])

crew_db_collection = db_instance.get_collection(collection="crew")
crew_repository = CrewRepository(db_collection=crew_db_collection)
crew_service = CrewService(repository=crew_repository)


@router.post(
    path="/get",
    response_description="Get all crew members' details",
    response_model=ResponseObject,
)
async def get_crew_members() -> JSONResponse:
    crew_collection: CrewCollection = await crew_service.get_all_crew_members()
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=crew_collection,
    )


@router.post(
    path="/get/{crew_member_id}",
    response_description="Get a spesific crew member's details",
    response_model=ResponseObject,
)
async def get_crew_member_by_id(crew_member_id: str) -> JSONResponse:
    crew_member: CrewModel = await crew_service.get_crew_member(
        crew_member_id=crew_member_id
    )
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=crew_member,
    )


@router.post(
    path="/create",
    response_description="Create a new crew member",
    response_model=ResponseObject,
)
async def create_crew_member(crew_model: CrewModel = Body(...)) -> JSONResponse:
    new_crew_member = await crew_service.create_crew_member(crew_member=crew_model)
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_CREATED,
        is_success=True,
        message="Successfully created new crew member",
        data=new_crew_member,
    )


@router.patch(
    path="/update/{crew_member_id}",
    response_description="Update an existing crew member",
    response_model=ResponseObject,
)
async def update_crew_member(
    crew_member_id: str, crew_model: CrewUpdateModel = Body(...)
) -> JSONResponse:
    await crew_service.update_crew_member(
        crew_member_id=crew_member_id, updated_data=crew_model
    )
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="Successfully update crew member",
        data=None,
    )


@router.delete(
    path="/delete/{crew_member_id}",
    response_description="Delete an existing crew member",
    response_model=ResponseObject,
)
async def delete_crew_member(crew_member_id: str) -> JSONResponse:
    await crew_service.delete_crew_member(crew_member_id=crew_member_id)
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="Successfully deleted crew member",
        data=None,
    )
