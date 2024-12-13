#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"
ROOT_DIR="$SCRIPT_DIR/../.."

FRONTEND_PATH="$ROOT_DIR/frontend"
BACKEND_PATH="$ROOT_DIR/Server"

pnpm concurrently -k \
  -n "frontend,server" \
  -c "bgBlue.bold,bgGreen.bold" \
  "cd $FRONTEND_PATH && pnpm dev" \
  "cd $BACKEND_PATH && ./mvnw spring-boot:run"
