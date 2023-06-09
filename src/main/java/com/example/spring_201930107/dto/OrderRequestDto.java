package com.example.spring_201930107.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class OrderRequestDto {
    private long productId;
    private int price;
}
