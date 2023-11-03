package com.example.shop.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Size(max = 50)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "username", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "shippingaddress", length = Integer.MAX_VALUE)
    private String shippingaddress;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "passwordhash", nullable = false)
    private String passwordhash;

    @Size(max = 12)
    @Column(name = "phonenumber", nullable = false)
    private String phonenumber;

    @Size(max = 20)
    @NotNull
    @Column(name = "userrole", nullable = false, length = 20)
    private String userrole;

}