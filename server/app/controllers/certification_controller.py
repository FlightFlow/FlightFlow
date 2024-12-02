from fastapi import APIRouter, Body
from fastapi.responses import JSONResponse

from app.database import db_instance
from app.models.certification_model import (
    CertificationCollection,
    CertificationModel,
    CertificationUpdateModel,
)
from app.repository.certification_repository import CertificationsRepository
from app.service.certification_service import CertificationService
from app.helpers.response_helper import ResponseHelper, ResponseObject
from app.helpers import status_helper

router: APIRouter = APIRouter(tags=["Certification Controller"])

certification_db_collection = db_instance.get_collection(collection="certification")
certification_repository = CertificationsRepository(
    db_collection=certification_db_collection
)
certification_service = CertificationService(repository=CertificationsRepository)


@router.post(
    path="/get",
    response_description="Get all certifications' details",
    response_model=ResponseObject,
)
async def get_certifications() -> JSONResponse:
    certification_collection: CertificationCollection = (
        await certification_service.get_all_certifications()
    )
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=certification_collection,
    )


@router.post(
    path="/get/{certification_id}",
    response_description="Get a spesific certification's details",
    response_model=ResponseObject,
)
async def get_certification_by_id(certification_id: str) -> JSONResponse:
    certification: CertificationCollection = (
        await certification_service.get_certification(certification_id=certification_id)
    )
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="",
        data=certification,
    )


@router.post(
    path="/create",
    response_description="Create a new certification",
    response_model=ResponseObject,
)
async def create_certification(
    certification_model: CertificationModel = Body(...),
) -> JSONResponse:
    new_certification = await certification_service.create_certification(
        certification=certification_model
    )
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_CREATED,
        is_success=True,
        message="Successfully created new certificate",
        data=new_certification,
    )


@router.patch(
    path="/update/{certification_id}",
    response_description="Update an existing certification",
    response_model=ResponseObject,
)
async def update_certification(
    certification_id: str, certification_model: CertificationModel = Body(...)
) -> JSONResponse:
    await certification_service.update_certification(
        certification_id=certification_id, updated_data=certification_model
    )
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="Successfully update certification",
        data=None,
    )


@router.delete(
    path="/delete/{certification_id}",
    response_description="Delete an existing certification",
    response_model=ResponseObject,
)
async def delete_certification(certification_id: str) -> JSONResponse:
    await certification_service.delete_certification(certification_id=certification_id)
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_SUCCESS,
        is_success=True,
        message="Successfully deleted certification",
        data=None,
    )
