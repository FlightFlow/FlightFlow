#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"
BACKEND_VENV_PATH="$SCRIPT_DIR/../backend/.venv"

source "$BACKEND_VENV_PATH/bin/activate"

echo "Info: Activated python virtual environment"
