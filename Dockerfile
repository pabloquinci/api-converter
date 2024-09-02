FROM openjdk:21-slim

LABEL mentainer="Backend Challenge - Possumus"

WORKDIR /app

COPY target/api-converter-0.0.1-SNAPSHOT.jar /app/api-converter-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "api-converter-0.0.1-SNAPSHOT.jar"]