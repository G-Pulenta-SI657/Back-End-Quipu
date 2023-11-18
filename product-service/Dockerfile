FROM openjdk:17

WORKDIR /app

COPY build/libs/product-service-0.0.1-SNAPSHOT.jar app-product-service.jar

EXPOSE 8090

CMD ["java", "-jar", "app-product-service.jar"]