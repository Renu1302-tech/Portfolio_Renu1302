# FROM maven:3.8.5-openjdk-17  AS build
# COPY . .
# RUN mvn clean package -DskipTests

# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /app/target/portfolio-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "app.jar"]

# ---- Build Stage ----
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory
WORKDIR /build

# Copy all project files
COPY . .

# Run Maven build
RUN mvn clean package -DskipTests

# ---- Run Stage ----
FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

# Copy the built JAR from /build/target to app.jar
COPY --from=build /build/target/portfolio-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
