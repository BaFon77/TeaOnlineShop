package com.example.shop.Service;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product getProductById(int id) {
        //TODO
        return productRepository.findById(id);
    }
}
