FROM openjdk:17-jdk-slim
VOLUME /tmp
ADD build/libs/service-registy-*.jar /service-registy.jar
ENV CONTAINER_NAME=localhost \
    PORT=8761 \
    OPTS_ARGS=''

ENTRYPOINT ["java", "-jar", "/service-registy.jar", "${OPTS_ARGS}"]