package com.example.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.Size(max = 255)
    @jakarta.validation.constraints.NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @jakarta.validation.constraints.NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @jakarta.validation.constraints.Size(max = 50)
    @jakarta.validation.constraints.NotNull
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @jakarta.validation.constraints.Size(max = 20)
    @Column(name = "weightvolume", length = 20)
    private String weightvolume;

    @jakarta.validation.constraints.NotNull
    @Column(name = "instock", nullable = false)
    private Boolean instock = false;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "photourl")
    private String photourl;

}