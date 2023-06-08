package com.example.spring_201930107.dao;

import com.example.spring_201930107.entity.User;

public interface SignDAO {
    User signUp(String id, String password, String name, String email, String role);
    String signIn(String id, String password);
}
