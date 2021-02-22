#! /bin/bash

sleep 10
cat /dbInit.js | sed "s/\${MONGO_DEFAULT_USER_PW}/${MONGO_DEFAULT_USER_PW}/g" > /dbInitInterpolated.sh
mongo --host ${MONGO_HOST}:27017/memex < /dbInitInterpolated.sh
