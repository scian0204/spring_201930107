package com.example.spring_201930107.service;

import com.example.spring_201930107.dto.SignInResultDto;
import com.example.spring_201930107.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String email, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
