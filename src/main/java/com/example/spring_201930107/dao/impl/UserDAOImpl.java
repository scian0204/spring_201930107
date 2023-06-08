package com.example.spring_201930107.dao.impl;

import com.example.spring_201930107.dao.UserDAO;
import com.example.spring_201930107.entity.User;
import com.example.spring_201930107.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.getByUid(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllBy();
    }

    @Override
    public List<User> getAllUsersOrderByName() {
        return userRepository.findAllByOrderByName();
    }
}
