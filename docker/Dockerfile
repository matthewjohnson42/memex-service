# Dockerfile for building a docker image from the "app" module of this project
FROM openjdk:13.0.2-jdk-slim
CMD mkdir /memex
COPY app/target/personal-memex-service-0.0.1.jar /memex/program.jar
COPY scripts/entrypoint.sh /memex/entrypoint.sh
ENTRYPOINT ["/bin/sh", "/memex/entrypoint.sh"]
