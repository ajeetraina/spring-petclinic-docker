#!/bin/bash

echo "Starting Spring PetClinic with Angular and PostgreSQL..."

# Check if PostgreSQL container is already running
if [ ! "$(docker ps -q -f name=petclinic-postgres)" ]; then
    if [ "$(docker ps -aq -f name=petclinic-postgres)" ]; then
        # Remove stopped container
        echo "Removing stopped PostgreSQL container..."
        docker rm petclinic-postgres
    fi
    # Start PostgreSQL container
    echo "Starting PostgreSQL container..."
    docker run --name petclinic-postgres -e POSTGRES_PASSWORD=petclinic -e POSTGRES_USER=petclinic -e POSTGRES_DB=petclinic -p 5432:5432 -d postgres:14-alpine
else
    echo "PostgreSQL container is already running."
fi

# Wait for PostgreSQL to start
echo "Waiting for PostgreSQL to start..."
sleep 5

# Run the Spring Boot application in the background with formatting checks skipped
echo "Starting Spring Boot backend..."
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres -Dcheckstyle.skip=true -Dspring-javaformat.skip=true &

# Wait for backend to start
echo "Waiting for backend to start..."
sleep 10

# Start Angular frontend
echo "Starting Angular frontend..."
cd angular-frontend
npm install
npm start

# When the user exits the Angular app, clean up the background processes
trap "kill %1; docker stop petclinic-postgres" EXIT
