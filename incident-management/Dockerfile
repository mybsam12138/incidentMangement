# Use a lightweight base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the application JAR file to the container
COPY target/incident-management.jar app.jar

# Expose the port on which the application runs
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]