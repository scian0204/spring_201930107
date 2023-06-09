package com.example.spring_201930107.service;

import com.example.spring_201930107.dto.OrderRequestDto;
import com.example.spring_201930107.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto buy(OrderRequestDto orderRequestDto, String token);

    List<OrderResponseDto> getAllOrders();

    List<OrderResponseDto> getAllOrdersByUserId(String userId);

    List<OrderResponseDto> getAllOrdersByProductId(long productId);

    OrderResponseDto getOrderById(Long id);
}
