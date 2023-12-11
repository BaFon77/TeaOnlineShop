package com.example.shop.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "tea_articles")
public class Articles implements Serializable {
    @Id
    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "tea_type")
    private String teaType;

}
