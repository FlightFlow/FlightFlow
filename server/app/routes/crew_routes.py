from fastapi.routing import APIRouter

router = APIRouter()


@router.post("")
async def test():
    return
