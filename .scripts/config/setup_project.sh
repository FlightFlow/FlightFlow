#!/bin/bash

# VARIABLES
SCRIPT_DIR="$(dirname "$(realpath "$0")")/.."

FRONTEND_PATH="$SCRIPT_DIR/../client"
BACKEND_PATH="$SCRIPT_DIR/../app"

# PRE-COMMIT HOOKS
echo "Info: Setting up pre-commit hook..."

ln -sf ../../.githooks/pre-commit.sh ../../.git/hooks/pre-commit

echo "Info: Pre-commit hook setup completed."

echo "---"

# .ENV FILES
rename_env_files() {
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

rename_env_files "$FRONTEND_PATH" "client"
rename_env_files "$BACKEND_PATH" "app"

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
