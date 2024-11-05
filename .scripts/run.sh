#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"

FRONTEND_PATH="$SCRIPT_DIR/../frontend"
BACKEND_PATH="$SCRIPT_DIR/../backend"

echo "$BACKEND_PATH"

pnpm concurrently -k \
  -n "frontend,backend" \
  -c "bgBlue.bold,bgGreen.bold" \
  "cd $FRONTEND_PATH && pnpm dev" \
  "bash -c 'cd $BACKEND_PATH/app && source ../../backend/.venv/bin/activate && uvicorn main:app --host 0.0.0.0 --port 8000 --reload'"
