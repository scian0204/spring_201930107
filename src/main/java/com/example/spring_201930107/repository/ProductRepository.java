package com.example.spring_201930107.repository;

import com.example.spring_201930107.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBy();
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findByName(String name);
}