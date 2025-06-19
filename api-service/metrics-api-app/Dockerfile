# Use a slim OpenJDK 17 image as the base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Declare an argument for the JAR file name.
# This assumes your Maven/Gradle build creates a JAR in the 'target' or 'build/libs' directory.
# For Maven: target/*.jar
# For Gradle: build/libs/*.jar
ARG JAR_FILE=target/*.jar

# Copy the application's JAR file into the container
COPY ${JAR_FILE} app.jar

EXPOSE 5050

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]