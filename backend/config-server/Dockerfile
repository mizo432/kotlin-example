FROM gradle:8.6.0-jdk21-alpine AS build

ARG BUILD_DIR=/home/build

COPY ./config-server ${BUILD_DIR}/config-server
COPY ./build.gradle ${BUILD_DIR}
COPY ./settings.gradle ${BUILD_DIR}

WORKDIR ${BUILD_DIR}

RUN gradle config-server:build

FROM openjdk:21.0-alpine

RUN mkdir /app

EXPOSE 8761

COPY --from=build "/home/build/config-server/build/libs/*.jar" /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]