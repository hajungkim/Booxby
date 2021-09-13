FROM openjdk:8-jdk-alpine

RUN chmod +x ./gradlew
RUN ./gradlew build

ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar

COPY --from=builder build/libs/*.jar app.jar

EXPOSE 7777
ENTRYPOINT [ "java", "-jar", "/app.jar", "--spring.profiles.active=prod" ]