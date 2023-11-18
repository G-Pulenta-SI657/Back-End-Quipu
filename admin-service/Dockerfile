# Usa una imagen base de Java 11 (puedes elegir otra versión si lo prefieres)
FROM openjdk:17

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de tu aplicación Spring Boot a la imagen (asegúrate de que el archivo JAR tenga el nombre correcto)
COPY build/libs/admin-service-0.0.1-SNAPSHOT.jar app-admin-service.jar

# Expone el puerto 8081 que configuraste en tu aplicación Spring Boot
EXPOSE 8086
# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app-admin-service.jar"]