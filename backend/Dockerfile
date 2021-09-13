FROM openjdk:8-jdk-alpine
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew build

ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 7777
ENTRYPOINT [ "java", "-jar", "/app.jar", "--spring.profiles.active=prod" ]