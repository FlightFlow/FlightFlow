#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"
ROOT_DIR="$SCRIPT_DIR/.."

FRONTEND_PATH="$ROOT_DIR/frontend"
BACKEND_PATH="$ROOT_DIR/server"
LOGIC_PATH="$ROOT_DIR/logic"

pnpm concurrently -k \
  -n "frontend,server" \
  -c "bgBlue.bold,bgGreen.bold" \
  "cd $FRONTEND_PATH && pnpm dev" \
  "cd $BACKEND_PATH && ./mvnw spring-boot:run" # \
# "cd $LOGIC_PATH && uvicorn main:app --host 0.0.0.0 --port 8000"
