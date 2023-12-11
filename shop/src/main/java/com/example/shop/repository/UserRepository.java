package com.example.shop.repository;

import com.example.shop.entity.Order;
import com.example.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String username);

    @Query(nativeQuery = true, value = """
    SELECT users.userrole
    FROM users
    WHERE users.username = :username
""")
    String findUserRoleByUsername(@Param("username") String username);
}