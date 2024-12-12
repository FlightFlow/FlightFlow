#!/bin/bash

# VARIABLES
SCRIPT_DIR="$(dirname "$(realpath "$0")")/.."

FRONTEND_PATH="$SCRIPT_DIR/../frontend"
BACKEND_PATH="$SCRIPT_DIR/../server"

# PRE-COMMIT HOOKS
echo "Info: Setting up pre-commit hook..."

ln -sf "$SCRIPT_DIR/../.githooks/pre-commit" "$SCRIPT_DIR/../.git/hooks/pre-commit"

echo "Info: Pre-commit hook setup completed."

echo "---"

# .ENV FILE IN FRONTEND
rename_frontend_env() {
  local path="$1"
  local env_file="$path/.env"
  local sample_file="$env_file.sample"
  local folder="$2"

  if [ ! -f "$env_file" ]; then
    cp "$sample_file" "$env_file"
    if [ -f "$env_file" ]; then
      echo "Info: Successfully renamed .env file in $folder. Now you can add variables."
    else 
      echo "Error: Failed to rename .env file in $folder."
      exit 1
    fi
  else
    echo "Info: env file is present in $folder. Skipping to content checks..."
    
    while IFS="=" read -r key value; do
      if [[ -z "$key" || "" =~ ^\s*# || "$value" =~ ^\s*$ ]]; then
        continue
      fi
      if [ -z "$value" ]; then
        echo "Error: '$key' variable is empty in $folder's .env file."
        exit 1
      fi
    done < "$env_file"

    echo -e "Info: $folder's .env file passed content checks."
  fi
}

rename_frontend_config_file() {
  local path="$1"
  local env_file="$path/.env"
  local sample_file="$env_file.sample"
  local folder="$2"

  if [ ! -f "$env_file" ]; then
    cp "$sample_file" "$env_file"
    if [ -f "$env_file" ]; then
      echo "Info: Successfully renamed .env file in $folder. Now you can add variables."
    else 
      echo "Error: Failed to rename .env file in $folder."
      exit 1
    fi
  else
    echo "Info: .env file is present in $folder."
  fi
}

rename_server_config_file() {
  local path="$1"
  local env_file="$path/src/main/resources/application.properties"
  local sample_file="$path/src/main/resources/example.application.properties"
  local folder="$2"

  if [ ! -f "$env_file" ]; then
    cp "$sample_file" "$env_file"
    if [ -f "$env_file" ]; then
      echo "Info: Successfully renamed .env file in $folder. Now you can add variables."
    else 
      echo "Error: Failed to rename .env file in $folder."
      exit 1
    fi
  else
    echo "Info: application.properties file is present in $folder."
  fi
}

rename_frontend_config_file "$FRONTEND_PATH" "frontend"
rename_server_config_file "$BACKEND_PATH" "server"

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
