#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"

FRONTEND_PATH="$SCRIPT_DIR/../frontend"
BACKEND_PATH="$SCRIPT_DIR/../server"

pnpm concurrently -k \
  -n "frontend,server" \
  -c "bgBlue.bold,bgGreen.bold" \
  "cd $FRONTEND_PATH && pnpm dev" \
  "cd $BACKEND_PATH && ./mvnw spring-boot:run"
