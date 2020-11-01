# script for building docker images and starting them using docker-compose
sh scripts/docker-build.sh
docker-compose --file docker/mongo/docker-compose.yml up --detach
docker-compose --file docker/elasticsearch/docker-compose.yml up --detach
echo "waiting for elastic to initialize" && sleep 15
docker-compose --file docker/docker-compose.yml up --detach
