package com.example.security.controller;

import com.example.security.entity.UserCredential;
import com.example.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @GetMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return service.saveUser((user));
    }

    @GetMapping("/token")
    public String getToken(UserCredential userCredential) {
        return service.generateToken(userCredential.getName());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
