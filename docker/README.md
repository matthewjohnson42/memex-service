# docker build module

Builds docker containers for the app, a Mongo instance, and an ElasticSearch instance. This comprises the full backend of the personal-memex.

## module contents

In addition to standard docker build files and docker container composition files, this maven module includes the following content:

#### `mongo/*`

A directory containing instructions for starting a configured and initialized mongo container. 

The directory content includes:
1) a JavaScript file containing DB init queries
1) a shell script that submits the JavaScript contents

#### `docker-build.sh`

Builds the Docker image of Mongo, runs a maven clean install on the project, and uses the output of the maven clean install to build a Docker image of the app.

#### `docker-compose-up.sh`

Invokes the build script, then uses docker-compose to start containers from the built images.

## process

Helper scripts prepare the execution environment for Docker image builds, run the Docker image builds, then use docker-compose to start docker containers from the built Docker images. Root helper script is `docker-compose-up.sh`.

Mongo uses a two-container start process. The first container is the container used by the running app. The second is a temporary container that initializes the first with default user data.

## docker technical design

Uses Docker volumes on the ElasticSearch and Mongo instances for data persistence.

Uses a Docker network to connect the containers.
