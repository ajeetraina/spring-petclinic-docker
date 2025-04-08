# Spring PetClinic with Angular and PostgreSQL

This repository contains an enhanced version of the Spring PetClinic application with Angular frontend and PostgreSQL backend.

## Features

- Spring Boot backend with REST APIs
- Angular frontend
- PostgreSQL database
- Docker and Docker Compose configuration

## Running the Application

### Using Docker Compose (Recommended)

```bash
./run-docker.sh
```

Or manually:

```bash
docker-compose -f docker-compose.angular-postgres.yml build
docker-compose -f docker-compose.angular-postgres.yml up
```

### For Local Development

1. Start a PostgreSQL container
```bash
docker run --name petclinic-postgres -e POSTGRES_PASSWORD=petclinic -e POSTGRES_USER=petclinic -e POSTGRES_DB=petclinic -p 5432:5432 -d postgres:14-alpine
```

2. Run the Spring Boot application
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres -Dcheckstyle.skip=true -Dspring-javaformat.skip=true
```

3. Install and run the Angular frontend
```bash
cd angular-frontend
npm install
npm start
```

## Access the Application

- Frontend: http://localhost:4200
- Backend API: http://localhost:8080/api
   
## Architecture

- **Backend**: Spring Boot application exposing REST APIs
- **Frontend**: Angular application with components for owners, pets, visits, and vets
- **Database**: PostgreSQL for data persistence

## API Endpoints

- `/api/owners`: Owner management
- `/api/pets`: Pet management
- `/api/vets`: Veterinarian management
- `/api/visits`: Visit management

## Directory Structure

```
.
├── angular-frontend/       # Angular frontend application
├── src/                    # Spring Boot backend application
├── docker-compose.yml      # Standard Docker Compose configuration
├── docker-compose.angular-postgres.yml # Docker Compose with Angular and PostgreSQL
├── Dockerfile              # Standard Dockerfile for Spring Boot app
├── Dockerfile.backend      # Dockerfile for Spring Boot with PostgreSQL
└── README.md
```

## License

This application is distributed under the Apache License v2.0.
