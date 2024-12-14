#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"
ROOT_DIR="$SCRIPT_DIR/../.."

FRONTEND_PATH="$ROOT_DOR/frontend"
BACKEND_PATH="$ROOT_DIR/server"

# PRE-COMMIT HOOKS
echo "Info: Setting up pre-commit hook..."

ln -sf "$ROOT_DIR/.githooks/pre-commit" "$ROOT_DIR/.git/hooks/pre-commit"

echo "Info: Pre-commit hook setup completed."

echo "---"

# .ENV FILE IN FRONTEND
rename_frontend_config_file() {
  local frontend_path="$1"
  local frontend_config_file="$frontend_path/.env"
  local frontend_config_sample_file="$frontend_config_file.sample"
  local folder_name="$2"

  if [ ! -f "$env_file" ]; then
    cp "$sample_file" "$env_file"
    if [ -f "$env_file" ]; then
      echo "Info: Successfully renamed .env file in $folder_name. Now you can add variables."
    else 
      echo "Error: Failed to rename .env file in $folder_name."
      exit 1
    fi
  else
    echo "Info: .env file is present in $folder."
  fi
}

rename_server_config_file() {
  local server_path="$1"
  local server_config_file="$server_path/src/main/resources/application.yml"
  local server_config_sample_file="$server_path/src/main/resources/example.application.yml"
  local folder_name="$2"

  if [ ! -f "$env_file" ]; then
    cp "$server_config_sample_file" "$server_config_file"
    if [ -f "$env_file" ]; then
      echo "Info: Successfully renamed application.yml file in $folder_name. Now you can add variables."
    else 
      echo "Error: Failed to rename application.yml file in $folder_name."
      exit 1
    fi
  else
    echo "Info: application.yml file is present in $folder."
  fi
}

rename_frontend_config_file "$FRONTEND_PATH" "frontend"
rename_server_config_file "$BACKEND_PATH" "server"

echo "---"

# SERVER SETUP
echo "Info: Setting up server dependencies..."

cd "$BACKEND_PATH" || exit 1
./mvnw clean install

echo "Info: Successfully set up server dependencies."

echo "---"

# FRONTEND DEPENDENCIES
echo "Info: Installing dependencies from package.json"

cd "$FRONTEND_PATH" || exit 1
pnpm install

echo "Info: Successfully installed dependencies from package.json"

echo "---"

# ROOT DEPENDENCIES
echo "Info: Installing root dependencies..."

cd "$SCRIPT_DIR" || exit 1
pnpm install

echo "Info: Successfully installed root dependencies."

exit 0
