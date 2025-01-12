#!/bin/bash

SCRIPT_DIR="$(dirname "$(realpath "$0")")"
ROOT_DIR="$SCRIPT_DIR/../.."

FRONTEND_PATH="$ROOT_DOR/frontend"
BACKEND_PATH="$ROOT_DIR/server"
LOGIC_PATH="$ROOT_DOR/logic"

DB_NAME="flightcoordinator_db"
DB_USER="local_user"
DB_PASSWORD="local_password"
POSTGRES_VERSION="14"
PG_HBA_FILE="/etc/postgresql/$POSTGRES_VERSION/main/pg_hba.conf"

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

# FRONTEND DEPENDENCIES
echo "Info: Installing frontend dependencies..."

cd "$FRONTEND_PATH" || exit 1
pnpm install

echo "Info: Successfully installed frontend dependencies."

echo "---"

# SERVER SETUP
echo "Info: Setting up server dependencies..."

cd "$BACKEND_PATH" || exit 1
./mvnw clean install

echo "Info: Successfully set up server dependencies."

echo "---"

# PYTHON VENV AND DEPENDENCIES
echo "Info: Setting up algorithm service's virtual environment..."

cd "$LOGIC_PATH" || exit 1
python3 -m venv .venv
pip install -r requirements.txt

echo "Info: Successfully set algorithm service's virtual environment..."

# ROOT DEPENDENCIES
echo "Info: Installing root dependencies..."

cd "$ROOT_DIR" || exit 1
pnpm install

echo "Info: Successfully installed root dependencies."

# POSTGRESQL
echo "Info: Starting PostgreSQL setup..."

start_postgres() {
  echo "Info: Starting PostgreSQL service..."
  sudo service postgresql start
  echo "Info: Successfully started PostgreSQL service."
}

setup_database() {
  echo "Info: Setting up PostgreSQL database, user, and privilages..."

  sudo -u postgres psql <<EOSQL
  CREATE DATABASE ${DB_NAME};
  CREATE USER ${DB_USER} WITH PASSWORD '${DB_USER}';
  GRANT ALL PRIVILEGES ON DATABASE ${DB_NAME} TO ${DB_USER};
  GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ${DB_USER};
  GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ${DB_USER};
  ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO ${DB_USER};
  ALTER USER ${DB_USER} WITH SUPERUSER;
  ALTER ROLE ${DB_USER} CREATEDB;
EOSQL

  echo "Info: Successfully set up PostgreSQL database, user, and privilages."
}

check_user_roles() {
  echo "Info: Checking PostgreSQL roles..."
  sudo -u $DB_USER psql -c "\du"
  echo "Info: Successfully checked PostgreSQL roles."
}

update_pg_hba() {
  echo "Info: Updating pg_hba.conf..."
  sudo sed -i '/^local[[:space:]]\+all[[:space:]]\+all[[:space:]]\+peer$/s/peer/md5/' "$PG_HBA_FILE" # TODO test this line
  echo "Info: Successfully updated pg_hba.conf..."
}

restart_postgres() {
  echo "Info: Restarting PostgreSQL service..."
  sudo systemctl restart postgresql
  echo "Info: Successfully PostgreSQL service."
}

test_connection() {
  echo "Info: Testing database connection..."
  psql -U ${DB_USER} -d ${DB_NAME} -d "\dt"
  echo "Info: Successfully tested database connection."
}

start_postgres
setup_database
check_user_roles
update_pg_hba
restart_postgres
test_connection

echo "Info: Successfully completed PostgreSQL setup."

exit 0
