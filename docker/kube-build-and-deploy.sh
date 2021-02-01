docker build --tag memex-mongo:0.0.1 --file mongo/Dockerfile ..
mvn clean install -f ../app/pom.xml
docker build --tag memex-service:0.0.1 --file Dockerfile ..
read -p "Enter kubernetes cluster IP for elasticsearch (from service-cidr, default 10.96.0.0/12): " ELASTIC_SEARCH_IP
cat elasticsearch/kube-deployment.yml | sed "s/\${ELASTICSEARCH_IP}/${ELASTICSEARCH_IP}/g" \
  > elasticsearch/interpolated-kube-deployment.yml
kubectl apply -f elasticsearch/interpolated-kube-deployment.yml
read -p "Enter kubernetes cluster IP for mongo (from service-cidr, default 10.96.0.0/12): " MONGO_IP
read -p "Enter encrypted password for default mongo user: " MONGO_DEFAULT_USER_PW
cat mongo/kube-deployment.yml | sed "s/\${MONGO_IP}/${MONGO_IP}/g" | \
  sed "s/\${MONGO_DEFAULT_USER_PW}/${MONGO_DEFAULT_USER_PW}/g" > mongo/interpolated-kube-deployment.yml
kubectl apply -f mongo/interpolated-kube-deployment.yml
dbsDeploying=true
while [ ${dbsDeploying} == "true" ]; do

done
read -p "Enter encryption key secret for JWT encryption: " TOKEN_ENC_KEY_SECRET
read -p "Enter encryption key secret for encryption of users' passwords: " USERPASS_ENC_KEY_SECRET
cat kube-deployment.yml | sed "s/\${TOKEN_ENC_KEY_SECRET}/${TOKEN_ENC_KEY_SECRET}/g" | \
  sed "s/\${USERPASS_ENC_KEY_SECRET}/${USERPASS_ENC_KEY_SECRET}/g" > interpolated-kube-deployment.yml
kubectl apply -f interpolated-kube-deployment.yml
