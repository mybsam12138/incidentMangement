
# Incident Management System

## Overview

This project is a simple **Incident Management System** built using **Spring Boot**. It provides a RESTful API for managing incidents, including functionalities to create, update, delete, and list incidents. The project is designed for simplicity, with in-memory data storage and no database integration.

## Features

- RESTful API for incident management:
    - Create, update, delete, and retrieve incidents.
- Spring Boot 2.7.5-based application.
- Docker and Kubernetes support for containerization and deployment.
- Includes unit tests for API functionality.
- Configurable with Java 17.

## Requirements

- Java 17
- Maven 3.6+
- Docker (optional, for containerization)
- Kubernetes (optional, for deployment)

## Third-Party Libraries
The project uses the following third-party libraries apart from the standard JDK:

### 1. **Spring Boot Starter Web**
- **Artifact**: `org.springframework.boot:spring-boot-starter-web`
- **Purpose**: Provides a robust framework for building web applications, including REST APIs. It simplifies the configuration and dependency management for web-related functionalities.

### 2. **Spring Boot Starter Test**
- **Artifact**: `org.springframework.boot:spring-boot-starter-test`
- **Purpose**: A comprehensive testing framework for Spring Boot applications. Includes JUnit, AssertJ, Mockito and other testing utilities for writing and executing unit tests.
- **Scope**: Test only.

### 3. **Lombok**
- **Artifact**: `org.projectlombok:lombok`
- **Version**: `1.18.24`
- **Purpose**: Reduces boilerplate code by generating getters, setters, constructors, and other common methods during compilation. Enhances code readability and maintainability.
- **Scope**: Provided (only needed at compile-time).

## Build and Run

### Using Maven
1. Build the application:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   java -jar target/incident-management.jar
   ```

### Using Docker
1. Build the Docker image:
   ```bash
   docker build -t incident-management .
   ```
2. Run the container:
   ```bash
   docker run -p 8080:8080 incident-management
   ```

### Using Kubernetes
1. Apply the Kubernetes configuration:
   ```bash
   kubectl apply -f deployment.yaml
   kubectl apply -f service.yaml
   ```
2. Access the application via the LoadBalancer or NodePort.

## API Endpoints
| HTTP Method | Endpoint             | Description                   |
|-------------|----------------------|-------------------------------|
| POST        | `/incidents`         | Create a new incident         |
| PUT         | `/incidents/{id}`    | Update an existing incident   |
| DELETE      | `/incidents/{id}`    | Delete an incident by ID      |
| GET         | `/incidents`         | List all incidents            |

## Testing
To run unit tests, use the following command:
```bash
mvn test
```




