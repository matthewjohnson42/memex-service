# entrypoint/startup script for the docker image containing the jar from the "app" module
# run when a docker container built from the docker image is started
cd /memex
java -Dspring.profiles.active=prod -classpath bcprov-jdk15on-1.68.jar:program.jar com.matthewjohnson42.personalMemexService.PersonalMemexServiceApplication
while [ true ]; do
  sleep 60
done
