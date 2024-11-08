# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Define the JAR_FILE argument with a default value
ARG JAR_FILE=target/tp-foyer-5.0.0.jar


# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Expose the port on which your Spring Boot application will run
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
