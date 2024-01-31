FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar /app/api.jar
COPY wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x /app/wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]