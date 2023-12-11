package com.example.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.*;


import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Size(max = 50)
    @NotNull
    @Column(name = "categoryid", nullable = false, length = 50)
    private String category;

    @Size(max = 20)
    @Column(name = "weightvolume", length = 20)
    private String weightvolume;

    @NotNull
    @Column(name = "instock", nullable = false)
    private Boolean instock = false;

    @Size(max = 255)
    @Column(name = "photourl")
    private String photourl;

}