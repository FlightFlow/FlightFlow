#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"
ROOT_DIR="$SCRIPT_DIR/.."

FRONTEND_PATH="$ROOT_DIR/frontend"
BACKEND_PATH="$ROOT_DIR/server"

build_frontend() {
  echo "Info: Building frontend..."

  cd "$FRONTEND_PATH"
  pnpm i
  pnpm run build

  if [ $? -eq 0 ]; then
    echo "Info: Successfully built frontend."
  else
    echo "Error: Failed to build frontend."
    exit 1
  fi
}

build_server() {
  echo "Info: Building backend server..."

  cd "$BACKEND_PATH"
  ./mvnw clean package

  if [ $? -eq 0 ]; then
    echo "Info: Successfully built backend server."
  else
    echo "Error: Failed to build backend server."
    exit 1
  fi
}


if [ "$1" == "frontend" ]; then
  build_frontend
elif [ "$1" == "server" ]; then
  build_server
elif [ "$1" == "full_app" ]; then
  build_frontend
  build_server
else
  echo "Usage: ./build.sh [frontend|server|full_app]"
  exit 1
fi
