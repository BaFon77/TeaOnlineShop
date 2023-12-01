package com.example.shop.Service;

import com.example.shop.entity.Order;
import com.example.shop.entity.User;
import com.example.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public List<Order> getUserOrdersByUser(String username) {
        System.err.println("1 "+username);
        return orderRepository.findByUsername(username);
    }

    public boolean processOrder(Long order_id, User request) {
        //TODO - process order
        return false;
    }
}
