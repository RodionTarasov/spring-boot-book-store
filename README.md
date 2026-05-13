# Spring Boot Book Store

## Description

Spring Boot REST API application for a book store.  
The project uses MySQL as a database and runs in Docker containers.

---

## Requirements

- Docker
- Docker Compose

---

## Setup

### 1. Create `.env` file

```bash
cp .env.template .env

2. Run application
docker compose up --build

Access
API URL: http://localhost:8088/api

Environment variables
MySQL
MYSQLDB_ROOT_PASSWORD
MYSQLDB_DATABASE
MYSQLDB_USER
MYSQLDB_PASSWORD
MYSQLDB_LOCAL_PORT
MYSQLDB_DOCKER_PORT
Spring Boot
SPRING_LOCAL_PORT
SPRING_DOCKER_PORT
DEBUG_PORT
Security

Never commit .env file to the repository.
Use .env.template instead.