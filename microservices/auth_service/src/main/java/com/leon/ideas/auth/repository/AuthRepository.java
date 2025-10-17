package com.leon.ideas.auth.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.bson.Document;

@Repository
public class AuthRepository {

    private final MongoTemplate usersMongoTemplate;

    @Autowired
    public AuthRepository(@Qualifier("usersMongoTemplate") MongoTemplate usersMongoTemplate) {
        this.usersMongoTemplate = usersMongoTemplate;
    }

    public Document findUserByEmail(String email) {
        Query query = new Query(Criteria.where("email").regex("^" + email + "$", "i"));
        return usersMongoTemplate.findOne(query, Document.class, "users");
    }
}