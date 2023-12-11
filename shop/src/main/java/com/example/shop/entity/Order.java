package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "orderdate")
    private Instant orderdate;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @NotNull
    @Column(name = "totalamount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalamount;

}