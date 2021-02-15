#! /bin/bash

# script for building Docker images of:
#   a mongo database,
#   an elasticsearch service,
#   the personal-memex service described by the "app" module of this project
# assumes build is for a production environment
# assumes directory of invocation is personal-memex-service/docker, parent of this script

docker build --tag memex-mongo:0.0.1 --file mongo/Dockerfile ..
mvn clean install -f ../app/pom.xml
docker build --tag memex-service:0.0.1 --file app/Dockerfile ..
