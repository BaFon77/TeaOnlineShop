package com.example.shop.repository;

import com.example.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(nativeQuery = true, value = """
    SELECT products.*
    FROM products
    JOIN productcategories p on products.categoryid = p.id 
    WHERE p.name = :name
""")
    List<Product> findByCategory(@Param("name") String name);
    Product findById(int id);

}

