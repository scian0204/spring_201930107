package com.example.spring_201930107.controller;

import com.example.spring_201930107.dto.UserInfoDto;
import com.example.spring_201930107.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "사용자 리스트 - ADMIN 만 볼 수 있음")
    @GetMapping("/list")
    public ResponseEntity<List<UserInfoDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @Operation(summary = "사용자 리스트 (이름순 정렬 - 오름차순) - ADMIN 만 볼 수 있음")
    @GetMapping("/listOrderByName")
    public ResponseEntity<List<UserInfoDto>> getAllUsersOrderByName() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsersOrderByName());
    }
}
