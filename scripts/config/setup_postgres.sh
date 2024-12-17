#!/bin/bash

DB_NAME="flightcoordinator_db"
DB_USER="local"
DB_PASSWORD="local"
POSTGRES_VERSION="14"
PG_HBA_FILE="/etc/postgresql/$POSTGRES_VERSION/main/pg_hba.conf"

# TODO test script

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

echo "Info: Successfully completed PostgreSQL setup."
