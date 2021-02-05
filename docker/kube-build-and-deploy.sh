docker build --tag memex-mongo:0.0.1 --file mongo/Dockerfile ..
mvn clean install -f ../app/pom.xml
docker build --tag memex-service:0.0.1 --file Dockerfile ..

read -p "Enter kubernetes cluster IP for elasticsearch (from service-cidr, default 10.96.0.0/12): " ELASTICSEARCH_IP
read -p "Enter kubernetes cluster IP for mongo (from service-cidr, default 10.96.0.0/12): " MONGO_IP
read -p "Enter encrypted password for default mongo user: " MONGO_DEFAULT_USER_PW
read -p "Enter kubernetes cluster IP for the memex-app (from service-cidr, default 10.96.0.0/12): " MEMEX_IP
read -p "Enter encryption key secret for JWT encryption: " TOKEN_ENC_KEY_SECRET
read -p "Enter encryption key secret for encryption of users' passwords: " USERPASS_ENC_KEY_SECRET

cat mongo/kube-meta.yml | sed "s/\${MONGO_IP}/${MONGO_IP}/g" | \
  sed "s/\${MONGO_DEFAULT_USER_PW}/${MONGO_DEFAULT_USER_PW}/g" > mongo/interpolated-kube-meta.yml
cat mongo/kube-deploy.yml | sed "s/\${MONGO_IP}/${MONGO_IP}/g" | \
  sed "s/\${MONGO_DEFAULT_USER_PW}/${MONGO_DEFAULT_USER_PW}/g" > mongo/interpolated-kube-deploy.yml
cat mongo/kube-init.yml | sed "s/\${MONGO_IP}/${MONGO_IP}/g" | \
  sed "s/\${MONGO_DEFAULT_USER_PW}/${MONGO_DEFAULT_USER_PW}/g" > mongo/interpolated-kube-init.yml
cat elasticsearch/kube-meta.yml | sed "s/\${ELASTICSEARCH_IP}/${ELASTICSEARCH_IP}/g" > elasticsearch/interpolated-kube-meta.yml
cat elasticsearch/kube-deploy.yml | sed "s/\${ELASTICSEARCH_IP}/${ELASTICSEARCH_IP}/g" > elasticsearch/interpolated-kube-deploy.yml
cat kube-meta.yml | sed "s/\${TOKEN_ENC_KEY_SECRET}/${TOKEN_ENC_KEY_SECRET}/g" | \
  sed "s/\${USERPASS_ENC_KEY_SECRET}/${USERPASS_ENC_KEY_SECRET}/g" | sed "s/\${MEMEX_IP}/${MEMEX_IP}/g" | \
  sed "s/\${MONGO_IP}/${MONGO_IP}/g" | sed "s/\${ELASTICSEARCH_IP}/${ELASTICSEARCH_IP}/g" > interpolated-kube-meta.yml
cat kube-deploy.yml | sed "s/\${TOKEN_ENC_KEY_SECRET}/${TOKEN_ENC_KEY_SECRET}/g" | \
  sed "s/\${USERPASS_ENC_KEY_SECRET}/${USERPASS_ENC_KEY_SECRET}/g" | sed "s/\${MEMEX_IP}/${MEMEX_IP}/g" | \
  sed "s/\${MONGO_IP}/${MONGO_IP}/g" | sed "s/\${ELASTICSEARCH_IP}/${ELASTICSEARCH_IP}/g" > interpolated-kube-deploy.yml

kubectl apply -f mongo/interpolated-kube-meta.yml
kubectl apply -f elasticsearch/interpolated-kube-meta.yml
kubectl apply -f interpolated-kube-meta.yml

kubectl apply -f elasticsearch/interpolated-kube-deploy.yml
kubectl apply -f mongo/interpolated-kube-deploy.yml
kubectl rollout status -w statefulset/memex-elasticsearch
kubectl rollout status -w statefulset/memex-mongo
kubectl apply -f mongo/interpolated-kube-init.yml

sleep 10 # wait for elastic search to initialize

kubectl apply -f interpolated-kube-deploy.yml
