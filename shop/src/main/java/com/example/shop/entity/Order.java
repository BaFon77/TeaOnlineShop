package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @Column(name = "orderdate")
    private Instant orderdate;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @jakarta.validation.constraints.NotNull
    @Column(name = "totalamount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalamount;

}