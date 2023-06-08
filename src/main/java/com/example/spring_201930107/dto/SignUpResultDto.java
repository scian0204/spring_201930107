package com.example.spring_201930107.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResultDto {
    private boolean success;
    private int code;
    private String msg;
}
