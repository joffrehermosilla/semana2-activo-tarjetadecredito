FROM adoptopenjdk/openjdk11:alpine-slim
EXPOSE 8080
ADD target/microservicio-activo-tarjetadecredito-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]