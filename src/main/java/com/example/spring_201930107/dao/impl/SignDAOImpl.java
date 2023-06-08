package com.example.spring_201930107.dao.impl;

import com.example.spring_201930107.config.security.JwtTokenProvider;
import com.example.spring_201930107.dao.SignDAO;
import com.example.spring_201930107.entity.User;
import com.example.spring_201930107.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SignDAOImpl implements SignDAO {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public User signUp(String id, String password, String name, String email, String role) {
        System.out.println("[signUp] 회원가입");
        User user;
        if (role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }
        return userRepository.save(user);
    }

    @Override
    public String signIn(String id, String password) throws RuntimeException{
        User user = userRepository.getByUid(id);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        return jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles());
    }
}
