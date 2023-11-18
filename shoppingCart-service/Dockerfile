FROM openjdk:17

WORKDIR /app

COPY build/libs/shoppingCart-service-0.0.1-SNAPSHOT.jar app-shoppingCart-service.jar

EXPOSE 8094

CMD ["java", "-jar", "app-shoppingCart-service.jar"]