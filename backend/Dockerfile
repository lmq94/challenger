


FROM amazoncorretto:17-alpine-jdk

LABEL authors="luke"

COPY  target/backend-0.0.1-SNAPSHOT.jar api/v1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api/v1.jar"]