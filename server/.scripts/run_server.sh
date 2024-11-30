#!/bin/bash

set -e

echo "Activating virtual environment..."
source .venv/bin/activate

echo "Running FlightCoordinator Server (Backend server only!)"
uvicorn app.main:app --host 0.0.0.0 --port 8000 --reload
