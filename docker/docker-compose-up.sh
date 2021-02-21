#! /bin/bash

# script for building docker images and starting them using docker-compose
# assumes directory of invocation is personal-memex-service/docker, parent of this script

docker build --tag memex-mongo:0.0.1 --file mongo/Dockerfile ..
mvn clean install -f ../app/pom.xml
docker build --tag memex-service:0.0.1 --file app/Dockerfile ..
docker-compose --file mongo/docker-compose.yml up --detach
docker-compose --file elasticsearch/docker-compose.yml up --detach
echo "waiting for elastic to initialize" && sleep 15
docker-compose --file docker-compose.yml up --detach
