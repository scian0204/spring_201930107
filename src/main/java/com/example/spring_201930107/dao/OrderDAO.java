package com.example.spring_201930107.dao;

import com.example.spring_201930107.dto.OrderRequestDto;
import com.example.spring_201930107.entity.Order;

import java.util.List;

public interface OrderDAO {
    Order buy(OrderRequestDto orderRequestDto, String token);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUserId(String userId);

    List<Order> getAllOrdersByProductId(String productId);

    Order getOrderById(Long id);
}
