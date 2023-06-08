package com.example.spring_201930107.dto;

import lombok.Data;

@Data
public class BoardChangeDto {
    private long id;
    private String title;
    private String contents;
}
