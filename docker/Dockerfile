#FROM openjdk:8-jdk-alpine
# openjdk:8-jdk-alpine with font support
# https://hub.docker.com/r/dquintela/openjdk-8-jdk-alpine/dockerfile
FROM dquintela/openjdk-8-jdk-alpine
COPY apps/app.jar app.jar
COPY run.sh run.sh
ENTRYPOINT sh run.sh && tail -f /dev/null