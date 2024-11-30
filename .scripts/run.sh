#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"

FRONTEND_PATH="$SCRIPT_DIR/../frontend"
SERVER_PATH="$SCRIPT_DIR/../server"

pnpm concurrently -k \
  -n "frontend,server" \
  -c "bgBlue.bold,bgGreen.bold" \
  "cd $FRONTEND_PATH && pnpm dev" \
  "bash -c 'cd $SERVER_PATH/app && source ../.venv/bin/activate && uvicorn main:app --host 0.0.0.0 --port 8000 --reload'"
