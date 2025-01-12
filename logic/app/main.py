from fastapi import FastAPI

app = FastAPI()


@app.get("/example")
async def example_func():
    return {"message": "Hello World!"}
