FROM eclipse-temurin:17

WORKDIR /app
COPY target/kafka-consumer-0.0.1-SNAPSHOT.jar /app/spring-boot-consumer.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar","spring-boot-consumer.jar"]