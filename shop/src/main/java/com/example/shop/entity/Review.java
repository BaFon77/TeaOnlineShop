package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "reviews", schema = "public")
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product productid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @jakarta.validation.constraints.NotNull
    @Column(name = "reviewtext", nullable = false, length = Integer.MAX_VALUE)
    private String reviewtext;

    @jakarta.validation.constraints.NotNull
    @Column(name = "rating", nullable = false)
    private Integer rating;

}