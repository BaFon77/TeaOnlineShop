package com.example.shop.Service;

import com.example.shop.entity.Order;
import com.example.shop.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    public List<Order> getUserOrders(String username) {
        //TODO
        return null;
    }

    public boolean processOrder(Long order_id, User request) {
        //TODO
        return false;
    }
}
