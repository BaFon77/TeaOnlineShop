package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String username;
    private String userRole;
    private String email;
    private String firstname;
    private String lastname;
    private String shippingAddress;
    private String phoneNumber;
}
