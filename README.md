# Springboot-HP-Management-backend API

A RESTful backend service built with Spring Boot for managing users, doctors, and appointments.

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate Validator
- Maven
- MySQL

## Features
- User registration and retrieval
- Doctor and appointment management
- Input validation using `@Valid`
- Global exception handling
- RESTful API design

## Configuration
Database credentials are configured in `application.properties`.
Sensitive values such as passwords should be provided via environment variables.

Example:
```properties
spring.datasource.password=${DB_PASSWORD}
