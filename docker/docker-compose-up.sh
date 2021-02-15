#! /bin/bash

# script for building docker images and starting them using docker-compose
# assumes directory of invocation is personal-memex-service/docker, parent of this script

sh docker-build.sh
docker-compose --file mongo/docker-compose.yml up --detach
docker-compose --file elasticsearch/docker-compose.yml up --detach
echo "waiting for elastic to initialize" && sleep 15
docker-compose --file docker-compose.yml up --detach
