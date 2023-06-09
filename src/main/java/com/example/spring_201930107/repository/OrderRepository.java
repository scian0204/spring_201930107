package com.example.spring_201930107.repository;

import com.example.spring_201930107.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(String userId);
    List<Order> findAllByProductId(long productId);
}