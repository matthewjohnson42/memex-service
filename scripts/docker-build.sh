# script for building Docker images of:
#   a mongo database,
#   an elasticsearch service,
#   the personal-memex service described by the "app" module of this project
# assumes build is for a production environment
docker build --tag memex-mongo:0.0.1 --file docker/mongo/Dockerfile .
docker build --tag memex-elastic:0.0.1 --file docker/elasticsearch/Dockerfile .
mvn clean install
mvn dependency:copy -Dartifact=org.bouncycastle:bcprov-jdk15on:1.68 -DoutputDirectory=${PWD}
docker build --tag memex-service:0.0.1 --file docker/Dockerfile .
