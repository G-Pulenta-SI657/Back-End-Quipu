FROM openjdk:17

WORKDIR /app

COPY build/libs/payment-service-0.0.1-SNAPSHOT.jar app-config-service.jar

EXPOSE 8093

CMD ["java", "-jar", "app-config-service.jar"]
