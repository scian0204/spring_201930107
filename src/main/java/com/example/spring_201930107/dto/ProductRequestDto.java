package com.example.spring_201930107.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private int price;
    private int stock;
}
