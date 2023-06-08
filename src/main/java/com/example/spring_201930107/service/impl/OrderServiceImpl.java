package com.example.spring_201930107.service.impl;

import com.example.spring_201930107.dao.OrderDAO;
import com.example.spring_201930107.dto.OrderRequestDto;
import com.example.spring_201930107.dto.OrderResponseDto;
import com.example.spring_201930107.entity.Order;
import com.example.spring_201930107.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Override
    public OrderResponseDto buy(OrderRequestDto orderRequestDto, String token) {
        return new OrderResponseDto(orderDAO.buy(orderRequestDto, token));
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderDAO.getAllOrders()
                .stream().map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByUserId(String userId) {
        return orderDAO.getAllOrdersByUserId(userId)
                .stream().map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getAllOrdersByProductId(String productId) {
        return orderDAO.getAllOrdersByProductId(productId)
                .stream().map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        return new OrderResponseDto(orderDAO.getOrderById(id));
    }
}
