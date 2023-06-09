package com.example.spring_201930107.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductUpdateDto {
    private Long number;
    private String name;
    private int price;
    private int stock;
}
