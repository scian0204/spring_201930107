package com.example.spring_201930107.controller;

import com.example.spring_201930107.dto.SignInResultDto;
import com.example.spring_201930107.dto.SignUpResultDto;
import com.example.spring_201930107.service.SignService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sign-api")
public class SignController {
    @Autowired
    SignService signService;

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResultDto> signUp(
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String role
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(signService.signUp(id, password, name, email, role));
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<SignInResultDto> signIn(@RequestParam String id, @RequestParam String password) {
        SignInResultDto signInResultDto = signService.signIn(id, password);
        if (signInResultDto.getCode() == 0) {
            System.out.println("[SignIn] 정상적으로 로그인되었습니다.");
            System.out.println("token: " + signInResultDto.getToken());
        }

        return ResponseEntity.status(HttpStatus.OK).body(signInResultDto);
    }

    @GetMapping("/exception")
    public void exception() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }
}
