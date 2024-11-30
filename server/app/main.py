from typing import Any, Dict

from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse
from fastapi.routing import APIRoute
from fastapi.openapi.utils import get_openapi

from app.config import AppConfig
from app.db import Database


def generate_unique_id(route: APIRoute) -> str:
    return f"{route.tags[0]}-{route.name}"


app = FastAPI(
    title=AppConfig.project_name,
    openapi_url="",
    generate_unique_id_function=generate_unique_id,
)
app.add_event_handler("startup", AppConfig.validate_config)
app.add_event_handler("startup", Database.connect)
app.add_event_handler("shutdown", Database.disconnect)


def openapi_schema() -> Dict[str, Any]:
    if app.openapi_schema:
        return openapi_schema
    openapi_schema = get_openapi(
        title=AppConfig.project_name,
        version=AppConfig.project_version,
        routes=app.routes,
    )
    app.openapi_schema = openapi_schema
    return app.openapi_schema


app.openapi = openapi_schema()

"""
@app.exception_handler()
async def bad_request(req: Request, exc) -> JSONResponse:
    return JSONResponse(status_code=500)


@app.get("/example")
async def example_func():
    return {"message": "Hello World!"}
"""
