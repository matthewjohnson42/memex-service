# Dockerfile for building a Mongo image
FROM mongo:4.2.8-bionic
COPY docker/mongo/dbInit.js /
COPY docker/mongo/dbInit.sh /
CMD ["cat 'sleep 60000' >> docker-entrypoint.sh"]
