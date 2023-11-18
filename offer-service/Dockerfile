FROM openjdk:17

WORKDIR /app

COPY build/libs/offer-service-0.0.1-SNAPSHOT.jar app-offer-service.jar

EXPOSE 8095

CMD ["java", "-jar", "app-offer-service.jar"]