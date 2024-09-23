# Dockerfile (optional)
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/app.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
