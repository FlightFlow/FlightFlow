from typing import List
from fastapi import APIRouter, Body
from fastapi.responses import JSONResponse

from app.database import db_instance
from app.models.crew_model import CrewCollection, CrewModel, CrewUpdateModel
from app.repository.crew_repository import CrewRepository
from app.service.crew_service import CrewService
from app.helpers.response_helper import ResponseHelper, ResponseObject
from app.helpers import status_helper
from app.service.enum_service import EnumService

router: APIRouter = APIRouter(tags=["Enum Controller"])

enum_service = EnumService()


@router.post(
    path="/get_crew_roles",
    response_description="Get all possible crew member roles",
    response_model=ResponseObject,
)
async def get_crew_roles() -> JSONResponse:
    crew_roles: List[str] = await enum_service.get_crew_roles()
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=crew_roles,
    )


@router.post(
    path="/get_cert_issuers",
    response_description="Get all possible certification issuers",
    response_model=ResponseObject,
)
async def get_cert_issuers() -> JSONResponse:
    cert_issuers: List[str] = await enum_service.get_cert_issuers()
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=cert_issuers,
    )


@router.post(
    path="/get_cert_countries",
    response_description="Get all possible countries which issued certification ara available in",
    response_model=ResponseObject,
)
async def get_cert_countries() -> JSONResponse:
    cert_countries: List[str] = await enum_service.get_cert_countries()
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=cert_countries,
    )


@router.post(
    path="/get_crew_availability",
    response_description="Get all possible crew member availability values",
    response_model=ResponseObject,
)
async def get_crew_availability() -> JSONResponse:
    crew_availability: List[str] = await enum_service.get_crew_availability()
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=crew_availability,
    )
