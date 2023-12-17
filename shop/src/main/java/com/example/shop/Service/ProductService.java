package com.example.shop.Service;

import com.example.shop.entity.Product;
import com.example.shop.entity.Productcategory;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Product getProductById(int id) {
        //TODO
        return productRepository.findById(id);
    }
    public boolean createProduct(Product product){
        try {
            productRepository.saveAndFlush(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteProduct(int id){
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

}
