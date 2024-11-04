#!/bin/bash

# VARIABLES
SCRIPT_DIR="$(dirname "$(realpath "$0")")"
ROOT_PATH="$SCRIPT_DIR/.."

FRONTEND_PATH="$SCRIPT_DIR/../frontend"
BACKEND_PATH="$SCRIPT_DIR/../backend"

REQUIREMENTS_FILE="$BACKEND_PATH/requirements.txt"
VENV_DIR="$BACKEND_PATH/.venv"

# PRE-COMMIT HOOKS
echo "Info: Setting up pre-commit hook..."

ln -sf ./githooks/pre-commit.sh ../.git/hooks/pre-commit

echo "Info: Pre-commit hook setup completed."

# .ENV FILES
rename_env_files() {
  local path="$1"
  local env_file="$path/.env"
  local sample_file="$env_file.sample"
  local folder

  if [ ! -f "$env_file" ]; then
    mv "$sample_file" "$env_file"
    if [ -f "$env_file" ]; then
      echo "Info: Successfully renamed .env file in $folder. Now you can add variables."
      exit 0 # Stop the further checks because .env file is newly created and is empty
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
        exit_with_error "Error: '$key' variable is empty in $folder's .env file."
      fi
    done < "$env_file"

    echo -e "Info: $folder's .env file passed content checks."
  fi
}

rename_env_files "$FRONTEND_PATH"
rename_env_files "$BACKEND_PATH"

# PYTHON VIRTUAL ENVIRONMENT
if [ ! -d "$REQUIREMENTS_FILE" ]; then
  echo "Error: requirements.txt not found in $BACKEND_PATH."
  exit 1
fi

if [ ! -d "$VENV_DIR" ]; then
  echo "Info: Setting up python virtual environment in $VENV_DIR..."
  python3 -m venv "$VENV_DIR"
  echo "Info: Successfully created python virtual environment."
else
  echo "Info: Python virtual environment already exists."
fi

# BACKEND DEPENDENCIES
echo "Info: Installing dependencies from requirements file..."

source "$VENV_DIR/bin/activate"
pip install --upgrade pip
pip install -r "$REQUIREMENTS_FILE"
deactivate

echo "Info: Successfully installed dependencies from requirements file."

# FRONTEND DEPENDENCIES
echo "Info: Installing dependencies from package.json"

cd "$FRONTEND_PATH" || exit 1
pnpm install

echo "Info: Successfully installed dependencies from package.json"

# ROOT DEPENDENCIES
echo "Info: Installing root dependencies..."

cd "$ROOT_PATH" || exit 1
pnpm install

echo "Info: Successfully installed root dependencies."

exit 0
