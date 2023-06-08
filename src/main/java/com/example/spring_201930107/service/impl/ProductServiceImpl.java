package com.example.spring_201930107.service.impl;

import com.example.spring_201930107.dao.ProductDAO;
import com.example.spring_201930107.dto.ProductRequestDto;
import com.example.spring_201930107.dto.ProductResponseDto;
import com.example.spring_201930107.dto.ProductUpdateDto;
import com.example.spring_201930107.entity.Product;
import com.example.spring_201930107.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return new ProductResponseDto(productDAO.addProduct(product));
    }

    @Override
    public ProductResponseDto updateProduct(ProductUpdateDto productUpdateDto) throws Exception{
        return new ProductResponseDto(productDAO.updateProduct(productUpdateDto.getNumber(), productUpdateDto.getName()));
    }

    @Override
    public void deleteProduct(long number) throws Exception{
        productDAO.deleteProduct(number);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productDAO.getAllProducts()
                .stream().map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getAllProductsOrderByPrice() {
        return productDAO.getAllProductsOrderByPrice()
                .stream().map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductResponseDto> getProductsByName(String name) {
        return productDAO.getProductsByName(name)
                .stream().map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProductByNumber(Long number) {
        return new ProductResponseDto(productDAO.getProductByNumber(number));
    }
}
