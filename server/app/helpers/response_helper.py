from typing import Union
from pydantic import BaseModel
from fastapi.responses import JSONResponse


class ResponseObject(BaseModel):
    isSuccess: bool
    message: str
    data: Union[None, object]


class ResponseHelper:
    @staticmethod
    def generate(
        status_code: int,
        is_success: bool,
        message: str,
        data: Union[None, object],
    ) -> JSONResponse:
        message = {
            "isSuccess": is_success,
            "message": message,
        }
        if data is not None:
            message["data"] = data
        else:
            message["data"] = "null"
        return JSONResponse(status_code=status_code, content=message)
