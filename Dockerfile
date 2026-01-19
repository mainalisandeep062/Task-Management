# Use official Eclipse Temurin JDK 17 image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the Maven-built JAR
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run your app
ENTRYPOINT ["java", "-jar", "app.jar"]
`