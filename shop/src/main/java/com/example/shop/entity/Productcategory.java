package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@Entity
@Table(name = "productcategories", schema = "public")
public class Productcategory {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentcategoryid")
    private Productcategory parentcategoryid;

}