package com.example.shop.controller;

import com.example.shop.Service.*;

import com.example.shop.dto.UserDto;
import com.example.shop.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    private String tokenUser;

    @GetMapping("/catalog/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return catalogService.getProductsByCategory(category);
    }

    @GetMapping("/catalog")
    public List<Productcategory> getCatalog() {
        return catalogService.getCatalog();
    }

    @PostMapping("/catalog")
    public ResponseEntity<Boolean> setCatalog(@RequestBody Productcategory productcategory) {
        boolean result = false;
        if (
                Objects.equals(userService.getUserRole(tokenUser), "ADMIN") ||
                        Objects.equals(userService.getUserRole(tokenUser), "admin")
        ) {
            result = catalogService.createCategory(productcategory);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/handle")
    public ResponseEntity<String> handleToken(@RequestBody String text) {
        tokenUser = text;
        return ResponseEntity.ok("Текст успешно получен");
    }

    @GetMapping("/orders")
    public List<Order> getUserOrders() { //TODO - add token verification
        System.err.println("Текст успешно получен" + "\n" + tokenUser);
        if (tokenUser != null) {
            return orderService.getUserOrdersByUser(tokenUser);
        } else return Collections.emptyList();
    }

    @PostMapping("/order/{order_id}")
    public ResponseEntity<Boolean> processOrder(@PathVariable Long order_id, @RequestBody User request) {
        // TODO - The logic of ordering
        boolean isOrderProcessed = orderService.processOrder(order_id, request);
        return ResponseEntity.ok(isOrderProcessed);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable String username) {
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(Integer.parseInt(id));
        return ResponseEntity.ok(product);
    }

    @PostMapping("/product/add")
    public ResponseEntity<Boolean> setProductById(@RequestBody Product product) {
        boolean result = false;
        if (
                Objects.equals(userService.getUserRole(tokenUser), "ADMIN") ||
                Objects.equals(userService.getUserRole(tokenUser), "admin")
        ) {
            result = productService.createProduct(product);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable int id) {
        boolean result = false;
        if (
                Objects.equals(userService.getUserRole(tokenUser), "ADMIN") ||
                Objects.equals(userService.getUserRole(tokenUser), "admin")
        ) {
            result = productService.deleteProduct(id);
        }
        return ResponseEntity.ok(result);
    }

    //TODO добавление статей

    @PostMapping("/article/add")
    public ResponseEntity<Boolean> setArticleById(@RequestBody Articles article) {
        boolean result = false;
        if (
                Objects.equals(userService.getUserRole(tokenUser), "MANAGER") ||
                Objects.equals(userService.getUserRole(tokenUser), "manager") ||
                Objects.equals(userService.getUserRole(tokenUser), "ADMIN")   ||
                Objects.equals(userService.getUserRole(tokenUser), "admin")
        ) {
            result = articleService.createArticle(article);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/article/{id}")
    public ResponseEntity<Boolean> deleteArticleById(@PathVariable long id) {
        boolean result = false;
        if (
                Objects.equals(userService.getUserRole(tokenUser), "MANAGER") ||
                Objects.equals(userService.getUserRole(tokenUser), "manager")
        ) {
            result = articleService.deleteArticle(id);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Articles> getArticleById(@PathVariable String id) {
        Articles article = articleService.getArticleById(Integer.parseInt(id));
        return ResponseEntity.ok(article);
    }
}
