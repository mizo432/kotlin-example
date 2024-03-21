FROM gradle:8.6.0-jdk21 AS build

ARG BUILD_DIR=/home/build

COPY ./service-registry ${BUILD_DIR}/service-registry
COPY ./build.gradle ${BUILD_DIR}
COPY ./settings.gradle ${BUILD_DIR}

WORKDIR ${BUILD_DIR}

RUN gradle service-registry:build

FROM eclipse-temurin:21.0.2_13-jdk-alpine

RUN mkdir /app

EXPOSE 8761

COPY --from=build "/home/build/service-registry/build/libs/*.jar" /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]