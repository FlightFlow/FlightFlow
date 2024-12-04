#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"

FRONTEND_PATH="$SCRIPT_DIR/../client"
BACKEND_PATH="$SCRIPT_DIR/../app"

pnpm concurrently -k \
  -n "frontend,server" \
  -c "bgBlue.bold,bgGreen.bold" \
  "cd $FRONTEND_PATH && pnpm dev" \
  "echo 'this line will be running backend (a.k.a. app)' sometime in the future"
