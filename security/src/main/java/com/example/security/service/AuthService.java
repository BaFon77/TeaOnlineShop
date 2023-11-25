package com.example.security.service;

import com.example.security.dto.AuthRequest;
import com.example.security.dto.AuthenticationResponse;
import com.example.security.dto.RegisterRequest;
import com.example.security.entity.UserCredential;
import com.example.security.repository.UserCredentialRepository;
import com.example.security.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse saveUser(RegisterRequest request) {
        var user = UserCredential.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(Role.USER)
                .build();
        System.out.println(user);
        repository.save(user);
        var jwtToken = jwtService.generateToken(request.getUsername());
        var refreshToken = jwtService.generateRefreshToken(request.getUsername());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthRequest request) {
        System.out.println(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        System.out.println("1");
        var jwtToken = jwtService.generateToken(request.getUsername());
        var refreshToken = jwtService.generateRefreshToken(request.getUsername());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
