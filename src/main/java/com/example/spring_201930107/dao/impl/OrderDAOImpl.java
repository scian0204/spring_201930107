package com.example.spring_201930107.dao.impl;

import com.example.spring_201930107.config.security.JwtTokenProvider;
import com.example.spring_201930107.dao.OrderDAO;
import com.example.spring_201930107.dao.ProductDAO;
import com.example.spring_201930107.dao.UserDAO;
import com.example.spring_201930107.dto.OrderRequestDto;
import com.example.spring_201930107.entity.Order;
import com.example.spring_201930107.entity.Product;
import com.example.spring_201930107.entity.User;
import com.example.spring_201930107.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ProductDAO productDAO;

    @Override
    public Order buy(OrderRequestDto orderRequestDto, String token) {
        Product product = productDAO.getProductByNumber(orderRequestDto.getProductId());
        Order order = new Order();
        if (product.getStock() > 0 && product.getPrice() <= orderRequestDto.getPrice()) {
            User user = userDAO.loadUserByUsername(jwtTokenProvider.getUsername(token));
            order.setProductId(product.getNumber());
            order.setProductName(product.getName());
            order.setUserId(user.getUid());
            order.setUserName(user.getName());
            order.setPrice(orderRequestDto.getPrice());
            order.setCreatedAt(LocalDateTime.now());
            order.setUpdatedAt(LocalDateTime.now());

            product.setStock(product.getStock()-1);
            productDAO.addProduct(product);
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersByUserId(String userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public List<Order> getAllOrdersByProductId(long productId) {
        return orderRepository.findAllByProductId(productId);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getReferenceById(id);
    }
}
