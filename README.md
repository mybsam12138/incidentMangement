# Incident Management Application

This is a full-stack Incident Management application that allows users to add, edit, delete, and list incidents. The project consists of a React-based frontend and a Spring Boot-based backend.

------

## Features

### Backend

- Built with **Spring Boot** and **Java 17**.
- RESTful APIs for managing incidents.
- Data stored in memory (no persistent database).
- Includes: 
  - Unit tests.
  - Integration tests.
  - Stress tests.

### Frontend

- Developed using **React**.
- Interacts with the backend via **Axios**.
- Key functionalities: 
  - Add new incidents by entering an `ID`, `Title`, and `Description`.
  - Edit or delete existing incidents.
- Planned Improvements
  - Add pagination for better handling of large data sets.
  - Enhanced UI/UX design for better usability.
  - Error handling with modal dialogs for displaying server-side error messages.

------

## Installation and Setup

### Prerequisites

- **Node.js** (v16 or higher) and **npm** for the frontend.
- **Java 17** for the backend.

### Backend Setup

1. Navigate to the backend project directory.

2. Run the Spring Boot application: 

   ```bash
   ./mvnw spring-boot:run
   ```

    Alternatively, you can execute the main class (

   ```
   IncidentManagementApplication
   ```

   ) in your IDE.

3. The backend will be available at `http://localhost:8080`.

### Frontend Setup

1. Navigate to the frontend project directory.

2. Install the dependencies: 

   ```bash
   npm install
   ```

3. Start the development server: 

   ```bash
   npm start
   ```

4. Access the application in your browser at `http://localhost:3000`.

------

## Running Tests

### Backend

- Unit Tests
  - Located in `src/test/java/com/sam/business/service`.
  - Located in `src/test/java/com/sam/business/controller`.
- Integration Tests
  - Located in `src/test/java/com/sam/business/integration`.
- Stress Tests
  - Located in `src/test/java/com/sam/business`.

Run tests directly from your IDE or using Maven:

```bash
./mvnw test
```

------

## Planned Enhancements

### Frontend

- Add pagination for better data handling.
- Improve UI/UX with responsive layouts and enhanced styling.
- Implement modal dialogs for error message displays.

------

## Tech Stack

- **Frontend**: React, Axios, Tailwind CSS.
- **Backend**: Spring Boot, Java 17.

------

## How to Use

1. Start the backend server.
2. Start the frontend server.
3. Open `http://localhost:3000` in your browser.
4. Add, edit, and delete incidents using the interface. Changes will be sent to the backend via RESTful API calls.

------

If you have questions or encounter issues, feel free to reach out!