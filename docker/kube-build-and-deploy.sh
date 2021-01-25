docker build --tag memex-mongo:0.0.1 --file mongo/Dockerfile ..
mvn clean install -f ../app/pom.xml
docker build --tag memex-service:0.0.1 --file Dockerfile ..
kubectl apply -f elasticsearch/kube-deployment.yml
kubectl apply -f mongo/kube-deployment.yml
#dbsDeploying=true
#while [ ${dbsDeploying} == "true" ]; do
#
#done
kubectl apply -f kube-deployment.yml
