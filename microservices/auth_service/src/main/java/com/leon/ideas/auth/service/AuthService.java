package com.leon.ideas.auth.service;

import com.leon.ideas.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.bson.Document;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    public ResponseEntity<Document> authenticateUser(String email, String password) {
        Document user = authRepository.findUserByEmail(email);
        if (user != null) {
            if (user.getString("password").equals(password)) {
                convertIdToString(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        Document errorResponse = new Document("error", "User not found or incorrect password");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Utility method to convert _id to string
    private void convertIdToString(Document document) {
        if (document.containsKey("_id")) {
            document.put("_id", document.getObjectId("_id").toString());
        }
    }
}