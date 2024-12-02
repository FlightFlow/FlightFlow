import os
import json
from fastapi import APIRouter, Body
from httpx import AsyncClient

from app.models.airport_model import AirportModel
from app.helpers.error_helper import AppError
from app.helpers import status_helper
from app.logger import logger

router = APIRouter(tags="External Endpoints")

external_api_url = "https://api.aviationstack.com/v1/airlines"
cache_file = "airports.json"


@router.post("/fetch_airlines")
async def fetch_airlines(access_key: str = Body(...), limit: int = Body(...)):
    params = {"access_key": access_key, "limit": limit}
    validated_airports = []
    async with AsyncClient() as client:
        try:
            response = await client.get(url=external_api_url, params=params)
            response.raise_for_status()
            raw_data = response.json()
            if "error" in raw_data:
                raise AppError(
                    status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                    message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
                )
            for airport in raw_data.get("data", []):
                try:
                    validated_airport = AirportModel(
                        name=airport.get("airport_name"),
                        iata_code=airport.get("iata_code"),
                        icao_code=airport.get("icao_code"),
                        country=airport.get("country_iso2"),
                        type="",
                        runways=[],
                    )
                    validated_airports.append(validated_airport)
                except Exception as exception:
                    logger.error(
                        f"An error ocurred while parsing external request data: {exception}"
                    )
                    raise AppError(
                        status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                        message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
                    )
        except exception:
            raise AppError(
                status_code=status_helper.STATUS_CODE_INTERNAL_SERVER_ERROR,
                message=status_helper.MESSAGE_INTERNAL_SERVER_ERROR,
            )
