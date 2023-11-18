FROM openjdk:17

WORKDIR /app

COPY build/libs/gateway-service-0.0.1-SNAPSHOT.jar app-gateway-service.jar

EXPOSE 8080

CMD ["java", "-jar", "app-gateway-service.jar"]