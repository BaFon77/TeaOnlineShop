package com.example.security.service;

import com.example.security.dto.ProfileResponse;
import com.example.security.entity.UserCredential;
import com.example.security.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserCredentialRepository repository;

    public ProfileResponse userInfo(String username) {
        Optional<UserCredential> userInfo= repository.findByUsername(username);
        return userInfo.map(user -> (new ProfileResponse(user.getUsername(), user.getUserRole().name(),
                user.getEmail(), user.getFirstname(), user.getLastname(),
                user.getShippingAddress(), user.getPhoneNumber()))).orElseThrow(() ->
                new UsernameNotFoundException("user not found :" + username));
    }

    public ProfileResponse updateUserProfile(String username, String newEmail, String newPhoneNumber,
                                             String newFirstName, String newLastName, String newAddress) {
        Optional<UserCredential> optionalProfile = repository.findByUsername(username);

        if (optionalProfile.isPresent()) {
            UserCredential profile = optionalProfile.get();
            profile.setEmail(newEmail);
            profile.setPhoneNumber(newPhoneNumber);
            profile.setFirstname(newFirstName);
            profile.setLastname(newLastName);
            profile.setShippingAddress(newAddress);

            repository.save(profile);

            return new ProfileResponse(profile.getUsername(), profile.getUserRole().name(), profile.getEmail(),
                    profile.getFirstname(), profile.getLastname(), profile.getShippingAddress(),
                    profile.getPhoneNumber());
        } else {
            return new ProfileResponse();
        }
    }
}
