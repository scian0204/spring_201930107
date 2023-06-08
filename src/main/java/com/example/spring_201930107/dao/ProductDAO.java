package com.example.spring_201930107.dao;

import com.example.spring_201930107.dto.ProductRequestDto;
import com.example.spring_201930107.dto.ProductUpdateDto;
import com.example.spring_201930107.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface ProductDAO {

    Product addProduct(Product product);

    Product updateProduct(Long number, String name) throws Exception;

    void deleteProduct(long number) throws Exception;
    List<Product> getAllProducts();
    List<Product> getAllProductsOrderByPrice();

    List<Product> getProductsByName(String name);

    Product getProductByNumber(Long number);
}
