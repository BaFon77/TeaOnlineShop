package com.example.shop.Service;

import com.example.shop.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    public User getUserByUsername(String username) {
        //TODO
        return null;
    }
}
