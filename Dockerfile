# Use an official maven image as a parent image
FROM maven:3.8.1-openjdk-17-slim as build

# Set the working directory in the image to /app
WORKDIR /app

# Copy the pom.xml file to our app directory
COPY pom.xml .

# Download all required dependencies into one layer
RUN mvn dependency:go-offline -B

# Copy the rest of the code
COPY src /app/src

# Package the application
RUN mvn package -DskipTests

# Start with a base image containing Java runtime
FROM openjdk:17-slim

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set the application's jar file
COPY --from=build /app/target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]