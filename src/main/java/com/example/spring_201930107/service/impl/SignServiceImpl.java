package com.example.spring_201930107.service.impl;

import com.example.spring_201930107.dao.SignDAO;
import com.example.spring_201930107.dto.CommonResponse;
import com.example.spring_201930107.dto.SignInResultDto;
import com.example.spring_201930107.dto.SignUpResultDto;
import com.example.spring_201930107.entity.User;
import com.example.spring_201930107.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    SignDAO signDAO;

    @Override
    public SignUpResultDto signUp(String id, String password, String name, String email, String role) {
        User user = signDAO.signUp(id, password, name, email, role);

        SignUpResultDto signUpResultDto = new SignUpResultDto();
        if (!user.getName().isEmpty()) {
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    @Override
    public SignInResultDto signIn(String id, String password) {
        String token = signDAO.signIn(id, password);
        SignInResultDto signInResultDto = SignInResultDto.builder().token(token).build();

        setSuccessResult(signInResultDto);

        return signInResultDto;
    }



    private void setSuccessResult(SignUpResultDto signUpResultDto) {
        signUpResultDto.setSuccess(true);
        signUpResultDto.setCode(CommonResponse.SUCCESS.getCode());
        signUpResultDto.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    private void setFailResult(SignUpResultDto signUpResultDto) {
        signUpResultDto.setSuccess(false);
        signUpResultDto.setCode(CommonResponse.FAIL.getCode());
        signUpResultDto.setMsg(CommonResponse.FAIL.getMsg());
    }
}
