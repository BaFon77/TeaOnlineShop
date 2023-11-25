package com.example.shop.controller;

import com.example.shop.Service.*;

import com.example.shop.dto.UserDto;
import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

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

    @GetMapping("/handle")
    public ResponseEntity<String> handleToken(@RequestBody String text) {
        tokenUser = text;
        return ResponseEntity.ok("Текст успешно получен");
    }

    @GetMapping("/orders")
    public List<Order> getUserOrders(@RequestParam String username) { //TODO - add token verification
        if (Objects.equals(username, tokenUser)) {
            return orderService.getUserOrders(username);
        } else return Collections.emptyList();
    }

    @PostMapping("/order/{order_id}")
    public ResponseEntity<Boolean> processOrder(@PathVariable Long order_id, @RequestBody User request) {
        // TODO - The logic of ordering
        boolean isOrderProcessed = orderService.processOrder(order_id, request);
        return ResponseEntity.ok(isOrderProcessed);
    }

    @GetMapping("/user/{username}") //TODO - Differentiate the received profile data
    public ResponseEntity<UserDto> getUserProfile(@PathVariable String username) {
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(Integer.parseInt(id));
        return ResponseEntity.ok(product);
    }
}
