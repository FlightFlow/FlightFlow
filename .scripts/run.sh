#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"

FRONTEND_PATH="$SCRIPT_DIR/../frontend"
BACKEND_PATH="$SCRIPT_DIR/../backend"

pnpm concurrently -k \
  -n "frontend,backend" \
  -c "bgBlue.bold,bgGreen.bold" \
  "cd $FRONTEND_PATH pnpm run dev" \
  "cd $BACKEND_PATH && source $BACKEND_PATH/.venv/bin/activate && uvicorn main:app --host 0.0.0.0 --port 8000 --reload"