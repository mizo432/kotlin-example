FROM gradle:8.6.0-jdk21 AS build

ARG BUILD_DIR=/home/build

COPY ./api-gateway ${BUILD_DIR}/api-gateway
COPY ./build.gradle ${BUILD_DIR}
COPY ./settings.gradle ${BUILD_DIR}

WORKDIR ${BUILD_DIR}

RUN gradle api-gateway:build

FROM openjdk:21.0-alpine

RUN mkdir /app

EXPOSE 8080

COPY --from=build "/home/build/api-gateway/build/libs/*.jar" /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]