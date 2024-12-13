#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"
ROOT_DIR="$SCRIPT_DIR/../.."

FRONTEND_PATH="$ROOT_DIR/frontend"
BACKEND_PATH="$ROOT_DIR/server"


echo "Info: Running frontend tests..."

cd "$FRONTEND_PATH"
pnpm test

echo "Info: Completed frontend tests."

echo "---"

echo "Info: Running server tests..."

cd "$BACKEND_PATH"
./mvnw test

echo "Info: Completed server tests."
