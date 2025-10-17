package com.leon.ideas.auth.configs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig {

    @Bean
    @Primary
    public MongoTemplate usersMongoTemplate() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/auth_db_dev");
        return new MongoTemplate(mongoClient, "auth_db_dev");
    }
}