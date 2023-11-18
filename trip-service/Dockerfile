FROM openjdk:17

WORKDIR /app

COPY build/libs/trip-service-0.0.1-SNAPSHOT.jar app-trip-service.jar

EXPOSE 8092

CMD ["java", "-jar", "app-trip-service.jar"]