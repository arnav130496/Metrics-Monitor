# Use a slim OpenJDK base image
FROM openjdk:17-jdk-slim

# Set argument for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Expose the port your Spring Boot app listens on (if it has HTTP endpoints like Actuator)
EXPOSE 9797

# Set the entry point to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]