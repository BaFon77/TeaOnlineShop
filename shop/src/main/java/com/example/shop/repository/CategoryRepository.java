package com.example.shop.repository;

import com.example.shop.entity.Productcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Productcategory, Integer> {

}
