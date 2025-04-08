# Spring PetClinic with Angular and PostgreSQL

This repository contains an enhanced version of the Spring PetClinic application with Angular frontend and PostgreSQL backend.

## Features

- Spring Boot backend with REST APIs
- Angular frontend
- PostgreSQL database
- Docker and Docker Compose configuration

## Running the Application

1. Clone this repository:

```bash
git clone https://github.com/ajeetraina/spring-petclinic-docker.git
cd spring-petclinic-docker
git checkout angular-postgres
```

2. Run the application using Docker Compose:

```bash
docker-compose -f docker-compose.angular-postgres.yml up
```

3. Access the application:
   - Backend: http://localhost:8080
   - Frontend: http://localhost:4200
   
## Architecture

- **Backend**: Spring Boot application exposing REST APIs
- **Frontend**: Angular application with components for owners, pets, visits, and vets
- **Database**: PostgreSQL for data persistence

## API Endpoints

- `/api/owners`: Owner management
- `/api/pets`: Pet management
- `/api/vets`: Veterinarian management
- `/api/visits`: Visit management

## Development

### Backend Development

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

### Frontend Development

```bash
cd angular-frontend
npm install
npm start
```

## License

This application is distributed under the Apache License v2.0.
