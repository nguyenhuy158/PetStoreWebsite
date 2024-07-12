#!/bin/bash

if command -v docker-compose >/dev/null 2>&1; then
	cmd="docker-compose"
else
	cmd="docker compose"
fi
function runDockerCompose() {
	$cmd -p docker -f src/main/docker/docker-compose.yml up -d
}

# Function to backup the database
function backupDatabase() {
    ./src/main/docker/script-backup-db.sh
}

# Function to restore the database
function restoreDatabase() {
    ./src/main/docker/script-restore-db.sh
}

# Function to clean and install Spring Boot
function cleanAndInstall() {
    ./mvnw clean install
}

# Function to run the server
function runServer() {
    ./mvnw spring-boot:run
}

# Function to clean all (stop server, clean Spring Boot and clean docker compose with volume)
function cleanAll() {
	backupDatabase
	
    # Stop server
    ./mvnw spring-boot:stop

    # Clean Spring Boot
    ./mvnw clean

    # Clean docker compose with volume
    $cmd -p docker -f src/main/docker/docker-compose.yml down -v
}

# Run all (restore database and run server)
function runAll() {
	runDockerCompose
    restoreDatabase
    runServer
}

# Main script

echo "Select an option:"
echo "1. Run docker compose"
echo "2. Backup database"
echo "3. Restore database"
echo "4. Clean and install Spring Boot"
echo "5. Run server"
echo "6. Clean all (stop server, clean Spring Boot and clean docker compose with volume)"
echo "7. Run all (restore database and run server)"

read -p "Option: " option

case $option in
    1) runDockerCompose;;
    2) backupDatabase;;
    3) restoreDatabase;;
    4) cleanAndInstall;;
    5) runServer;;
    6) cleanAll;;
    7) runAll;;
    *) echo "Invalid option";;
esac