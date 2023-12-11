package com.example.shop.repository;

import com.example.shop.entity.Articles;
import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticlesRepository extends JpaRepository<Articles, Long>, JpaSpecificationExecutor<Articles> {
    Articles findByArticleId(int id);
}