package com.example.spring_201930107.service.impl;

import com.example.spring_201930107.dao.UserDAO;
import com.example.spring_201930107.dto.UserInfoDto;
import com.example.spring_201930107.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public List<UserInfoDto> getAllUsers() {
        return userDAO.getAllUsers()
                .stream().map(UserInfoDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserInfoDto> getAllUsersOrderByName() {
        return userDAO.getAllUsersOrderByName()
                .stream().map(UserInfoDto::new)
                .collect(Collectors.toList());
    }
}
