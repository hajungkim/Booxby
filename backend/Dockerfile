FROM openjdk:8-jdk-alpine

RUN sudo chmod +x ./gradlew
RUN sudo ./gradlew build

ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 7777
ENTRYPOINT [ "java", "-jar", "/app.jar", "--spring.profiles.active=prod" ]