FROM openjdk:21

COPY target/*.jar app.jar

EXPOSE 8080
LABEL authors="Giannis Arkadianos"

ENTRYPOINT ["java", "-jar", "/app.jar"]