#! /bin/bash

# script for building docker images and starting them using docker-compose
# assumes directory of invocation is personal-memex-service/docker, parent of this script

docker build --tag memex-mongo:0.0.1 --file mongo/Dockerfile ..
mvn clean install -f ../app/pom.xml
docker build --tag memex-service:0.0.1 --file app/Dockerfile ..
docker-compose --file mongo/docker-compose.yml up --detach
echo "waiting for mongo to initialize" && sleep 5
cat mongo/dbInit.js | sed "s/\${MONGO_DEFAULT_USER_PW}/${MONGO_DEFAULT_USER_PW}/g" > mongo/dbInitInterpolated.js
mongo < mongo/dbInitInterpolated.js
docker-compose --file elasticsearch/docker-compose.yml up --detach
echo "waiting for elastic to initialize" && sleep 15
docker-compose --file app/docker-compose.yml up --detach
