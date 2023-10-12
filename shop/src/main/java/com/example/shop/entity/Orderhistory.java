package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "orderhistory", schema = "public")
public class Orderhistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderhistory_gen")
    @SequenceGenerator(name = "orderhistory_gen", sequenceName = "orderhistory_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Order orderid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @Column(name = "orderdate")
    private Instant orderdate;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

}