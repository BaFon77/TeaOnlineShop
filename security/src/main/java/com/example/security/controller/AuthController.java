package com.example.security.controller;

import com.example.security.dto.*;
import com.example.security.service.AuthService;
import com.example.security.service.CustomUserDetailsService;
import com.example.security.service.JwtService;
import com.example.security.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;

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

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        String username = jwtService.getClaims(token).get("sub", String.class);
        ProfileResponse user = profileService.userInfo(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<ProfileResponse> saveProfile(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestBody ProfileRequest profileRequest) {

        String token = authorizationHeader.substring(7);

        String username = jwtService.getClaims(token).get("sub", String.class);

        ProfileResponse response = profileService.updateUserProfile(username, profileRequest.getEmail(),
                profileRequest.getPhoneNumber(), profileRequest.getFirstname(), profileRequest.getLastname(),
                profileRequest.getShippingAddress());
        return ResponseEntity.ok(response);
    }
}
