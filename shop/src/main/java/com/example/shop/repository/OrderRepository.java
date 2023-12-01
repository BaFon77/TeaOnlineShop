package com.example.shop.repository;

import com.example.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(nativeQuery = true, value = """
    SELECT orders.*
    FROM orders
    JOIN users ON orders.userId = users.id
    WHERE users.username = :username
""")
    List<Order> findByUsername(@Param("username") String username);
}
