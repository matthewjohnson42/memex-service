package com.matthewjohnson42.memexService.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * Class that provides a MongoClient for connecting to MongoDB and a MongoTemplate for making modifications to Mongo
 */
@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${db.mongo.host}")
    private String hostName;

    @Value("${db.mongo.port}")
    private String dbPort;

    @Override
    public void configureClientSettings(MongoClientSettings.Builder builder) {
        ConnectionString connectionString = new ConnectionString(String.format("mongodb://%s:%s", hostName, dbPort));
        builder.applyConnectionString(connectionString);
    }

    @Override
    protected String getDatabaseName() {
        return "memex";
    }

}
