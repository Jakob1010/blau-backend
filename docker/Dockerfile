# Use the official Gradle image to build the Spring app
FROM gradle:7.2.0-jdk17 AS builder
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY src /app/src
RUN gradle clean build --no-daemon

# Use the official OpenJDK image as the base image
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the built Spring app from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port that the Spring app will run on
EXPOSE 8080

# Run the Spring app
CMD ["java", "-jar", "app.jar"]

