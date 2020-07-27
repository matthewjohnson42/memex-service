# entrypoint/startup script for the docker image containing the jar from the "app" module
# run when a docker container built from the docker image is started
java -jar /memex/program.jar
