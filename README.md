# Memex Service

A Java application that implements a knowledge base, named for the [Memex](https://en.wikipedia.org/wiki/Memex) described in a 1945 Atlantic article.

The purpose of the application is to store notes with tags so that those notes may be later referenced across months and years.

The application is not currently in use. I have shifted to use of a purchased product.

The application makes extensive use of the Spring Boot framework, uses Mongo DB and Elastic Search for data storage, and interacts with [memex-ui](https://github.com/matthewjohnson42/memex-ui) as its frontend.

The configuration of the host on AWS is provided by [k8s-standalone](https://github.com/matthewjohnson42/k8s-standalone).

### Using the service locally
To run the Java application, install Docker desktop, install maven, and run the [docker-compose-up.sh](https://github.com/matthewjohnson42/memex-service/docker/docker-compose-up.sh) script.
