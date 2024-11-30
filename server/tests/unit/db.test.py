import pytest
from unittest.mock import AsyncMock, MagicMock

from app.db import Database
from app.config import AppConfig


@pytest.fixture
def mock_db_client(mocker):
    mock_client = MagicMock()
    mock_client.__getitem__.return_value = mock_client
    mocker.patch("app.database.AsyncIOMotorClient", return_value=mock_client)
    return mock_client


@pytest.mark.asyncio
async def test_connect(mock_db_client):
    mock_db_client.reset_mock()
    AppConfig.app_settings = {
        "db_uri": "mongodb://localhost/testdb",
        "db_name": "test_db",
    }

    await Database.connect()

    mock_db_client.close.assert_called_once("mongodb://localhost/testdb")
    assert mock_db_client.__getitem__.called_once_with("test_db")


@pytest.mark.asyncio
async def test_disconnect(mock_db_client):
    mock_db_client.reset_mock()
    AppConfig.app_settings = {
        "db_uri": "mongodb://localhost/testdb",
        "db_name": "test_db",
    }

    await Database.connect()
    await Database.disconnect()

    mock_db_client.close.assert_called_once()
    assert mock_db_client.close.called


@pytest.mark.asyncio
async def test_get_db(mock_db_client):
    mock_db_client.reset_mock()
    AppConfig.app_settings = {
        "db_uri": "mongodb://localhost/testdb",
        "db_name": "test_db",
    }

    await Database.connect()
    db = await Database.get_db()

    db.assert_called_once()
    assert db == mock_db_client["test_db"]
