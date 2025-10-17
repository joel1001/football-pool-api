package com.leon.ideas.auth.controller;

import com.leon.ideas.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.bson.Document;

@RestController
@RequestMapping("/football-pool/v1/api/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<Document> login(@RequestBody Document body) {
        String email = body.getString("email");
        String password = body.getString("password");
        return authService.authenticateUser(email, password);
    }
}
