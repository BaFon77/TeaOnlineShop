package com.example.security.controller;

import com.example.security.dto.AuthRequest;
import com.example.security.dto.AuthenticationResponse;
import com.example.security.dto.RegisterRequest;
import com.example.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> addNewUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.saveUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> getToken(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest);
        return ResponseEntity.ok(service.authenticate(authRequest));
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
