package com.example.shop.Service;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CatalogService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
