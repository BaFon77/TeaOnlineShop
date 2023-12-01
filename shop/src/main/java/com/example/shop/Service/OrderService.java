package com.example.shop.Service;

import com.example.shop.entity.Order;
import com.example.shop.entity.User;
import com.example.shop.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private OrderRepository orderRepository;
    public List<Order> getUserOrders(String username) {
        return orderRepository.findByUserid(username);
    }

    public boolean processOrder(Long order_id, User request) {
        //TODO - process order
        return false;
    }
}
