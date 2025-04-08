#!/bin/bash

echo "Building and running Spring PetClinic with Angular and PostgreSQL using Docker Compose..."

# Build the images
docker-compose -f docker-compose.angular-postgres.yml build

# Run the containers
docker-compose -f docker-compose.angular-postgres.yml up
