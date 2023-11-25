package com.example.shop.Service;

import com.example.shop.dto.UserDto;
import com.example.shop.entity.User;
import com.example.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByName(username);
        return new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getName(), user.getUserrole());
    }
    public User getPrivatUserData(String username) {
        return userRepository.findByName(username);
    }
}
