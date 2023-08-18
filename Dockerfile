# Use the official Gradle image to build the application
FROM gradle:8.2.1-jdk17 AS build

# Set the working directory inside the container
WORKDIR /workspace

# Copy the Gradle build files
COPY build.gradle settings.gradle /workspace/
COPY gradle /workspace/gradle

# Copy the application source code
COPY src /workspace/src

# Build the application
RUN gradle build --no-daemon --scan

# Use a smaller base image for the runtime environment
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Spring application JAR from the build stage
COPY --from=build /workspace/build/libs/blau-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your Spring application will run on
EXPOSE 8080

# Command to run your Spring application
CMD ["java", "-jar", "app.jar"]
