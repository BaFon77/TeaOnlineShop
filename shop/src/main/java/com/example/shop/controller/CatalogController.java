package com.example.shop.controller;

import com.example.shop.Service.*;

import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return catalogService.getProductsByCategory(category);
    }

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getUserOrders(@RequestParam String username) {
        // TODO Логика получения истории заказов и текущих заказов
        return orderService.getUserOrders(username);
    }

    @PostMapping("/order/{order_id}")
    public ResponseEntity<Boolean> processOrder(@PathVariable Long order_id, @RequestBody User request) {
        // TODO Логика оформления заказа
        boolean isOrderProcessed = orderService.processOrder(order_id, request);
        return ResponseEntity.ok(isOrderProcessed);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserProfile(@PathVariable String username) {
        // TODO Логика загрузки личного кабинета пользователя
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(Integer.parseInt(id));
        return ResponseEntity.ok(product);
    }
}
