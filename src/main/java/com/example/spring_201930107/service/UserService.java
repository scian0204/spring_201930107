package com.example.spring_201930107.service;

import com.example.spring_201930107.dto.UserInfoDto;

import java.util.List;

public interface UserService {

    List<UserInfoDto> getAllUsers();

    List<UserInfoDto> getAllUsersOrderByName();
}
