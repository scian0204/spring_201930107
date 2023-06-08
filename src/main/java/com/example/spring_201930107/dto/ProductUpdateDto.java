package com.example.spring_201930107.dto;

public class ProductUpdateDto {
    private Long number;
    private String name;

    public ProductUpdateDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
