package com.example.spring_201930107.service;

import com.example.spring_201930107.dto.ProductRequestDto;
import com.example.spring_201930107.dto.ProductResponseDto;
import com.example.spring_201930107.dto.ProductUpdateDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(ProductUpdateDto productUpdateDto) throws Exception;
    void deleteProduct(long number) throws Exception;
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getAllProductsOrderByPrice();

    List<ProductResponseDto> getProductsByName(String name);

    ProductResponseDto getProductByNumber(Long number);
}
