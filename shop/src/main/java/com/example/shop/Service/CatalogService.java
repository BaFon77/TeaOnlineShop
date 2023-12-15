package com.example.shop.Service;

import com.example.shop.entity.Product;
import com.example.shop.entity.Productcategory;
import com.example.shop.repository.CategoryRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public boolean createCategory(Productcategory productcategory){
        try {
            categoryRepository.saveAndFlush(productcategory);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Productcategory> getCatalog() {
        return categoryRepository.findAll();
    }
}
