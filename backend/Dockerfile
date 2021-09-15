FROM adoptopenjdk:8-jdk-hotspot AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod 777 ./gradlew
RUN ./gradlew build

FROM adoptopenjdk:8-jdk-hotspot
COPY --from=builder build/libs/booxby-*.jar app.jar


EXPOSE 7777
ENTRYPOINT [ "java", "-jar", "/app.jar", "--spring.profiles.active=prod" ]
