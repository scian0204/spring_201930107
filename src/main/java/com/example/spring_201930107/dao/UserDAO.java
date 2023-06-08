package com.example.spring_201930107.dao;

import com.example.spring_201930107.entity.User;

import java.util.List;

public interface UserDAO {
    User loadUserByUsername(String username);

    List<User> getAllUsers();

    List<User> getAllUsersOrderByName();
}
