#!/bin/bash

echo "Starting Spring PetClinic with Angular and PostgreSQL..."

# Start PostgreSQL container
docker run --name petclinic-postgres -e POSTGRES_PASSWORD=petclinic -e POSTGRES_USER=petclinic -e POSTGRES_DB=petclinic -p 5432:5432 -d postgres:14-alpine

# Wait for PostgreSQL to start
echo "Waiting for PostgreSQL to start..."
sleep 5

# Run the Spring Boot application
echo "Starting Spring Boot backend..."
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres &

# Wait for backend to start
echo "Waiting for backend to start..."
sleep 10

# Start Angular frontend
echo "Starting Angular frontend..."
cd angular-frontend
npm install
npm start
