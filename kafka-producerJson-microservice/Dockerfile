FROM eclipse-temurin:17

WORKDIR /app
COPY target/kafka-producer-0.0.1-SNAPSHOT.jar /app/spring-boot-producer.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar","spring-boot-producer.jar"]