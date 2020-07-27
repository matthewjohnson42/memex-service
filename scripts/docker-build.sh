# script for building Docker images of:
#   a mongo database,
#   an elasticsearch service,
#   the personal-memex service described by the "app" module of this project
docker build --tag memex-mongo:0.0.1 --file docker/mongo/Dockerfile .
docker build --tag memex-elastic:0.0.1 --file docker/elasticsearch/Dockerfile .
mvn clean install
docker build --tag memex-service:0.0.1 --file docker/Dockerfile .
