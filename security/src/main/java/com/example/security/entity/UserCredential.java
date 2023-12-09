package com.example.security.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserCredential {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @OneToMany(mappedBy = "user")
//    private List<Token> tokens;

    @Size(max = 50)
    @NotNull
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Size(max = 50)
    @Column(name = "firstname", length = 50)
    private String firstname;

    @Size(max = 50)
    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "shippingaddress", length = Integer.MAX_VALUE)
    private String shippingAddress;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "passwordhash", nullable = false)
    private String password;

    @Size(max = 12)
    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

//    @Size(max = 20)
//    @NotNull
    @Column(name = "userrole", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Role userRole;

}
