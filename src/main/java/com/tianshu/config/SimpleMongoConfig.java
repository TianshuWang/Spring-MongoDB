package com.tianshu.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

//@Configuration
public class SimpleMongoConfig {

    @Value("${mongodb.url}")
    private String mongodbURL;

    @Value("${mongodb.database-name")
    private String databaseName;

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(mongodbURL);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), databaseName);
    }
}
