package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orderedproducts", schema = "public")
public class Orderedproduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderedproducts_gen")
    @SequenceGenerator(name = "orderedproducts_gen", sequenceName = "orderedproducts_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Order orderid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product productid;

    @jakarta.validation.constraints.NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @jakarta.validation.constraints.NotNull
    @Column(name = "unitprice", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitprice;

    @jakarta.validation.constraints.NotNull
    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

}