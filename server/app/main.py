from fastapi import Depends, FastAPI, Request, HTTPException
from fastapi.exceptions import RequestValidationError
from pydantic import ValidationError

from app.config import AppConfig
from app.database import db_instance
from app.helpers.response_helper import ResponseHelper
from app.helpers.error_helper import AppError
from app.helpers import status_helper

from app.controllers.enum_controller import router as EnumRouter
from app.controllers.crew_controller import router as CrewRouter
from app.controllers.certification_controller import router as CertificationRouter


def is_dev() -> bool:
    AppConfig.validate_config()
    app_env = AppConfig.app_settings.get("app_env")
    return True if app_env == "dev" else False


app: FastAPI = FastAPI(
    title=AppConfig.project_name,
    description=AppConfig.project_desc,
    version=AppConfig.project_version,
    openapi_url="/openapi",
    debug=is_dev(),
)
app.add_event_handler("startup", AppConfig.validate_config)
app.add_event_handler("shutdown", db_instance.disconnect)

app.include_router(prefix="/api/enums", router=EnumRouter)
app.include_router(prefix="/api/crew", router=CrewRouter)
app.include_router(prefix="/api/certification", router=CertificationRouter)


async def app_error(request: Request, exception: AppError):
    return ResponseHelper.generate(
        status_code=exception.status_code,
        is_success=False,
        message=exception.message,
        data=None,
    )


async def unknown_error(request: Request, exception: Exception):
    return ResponseHelper.generate(
        status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
        is_success=False,
        message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
        data=None,
    )


app.add_exception_handler(AppError, app_error)
app.add_exception_handler(ValidationError, unknown_error)
app.add_exception_handler(HTTPException, unknown_error)
app.add_exception_handler(RequestValidationError, unknown_error)
app.add_exception_handler(Exception, unknown_error)
