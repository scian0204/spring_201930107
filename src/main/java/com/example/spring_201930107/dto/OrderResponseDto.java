package com.example.spring_201930107.dto;

import com.example.spring_201930107.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderResponseDto {
    private long id;
    private String productId;
    private String productName;
    private String userId;
    private String userName;
    private int price;
    private LocalDateTime createdAt;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.productId = order.getProductId();
        this.productName = order.getProductName();
        this.userId = order.getUserId();
        this.userName = order.getUserName();
        this.price = order.getPrice();
        this.createdAt = order.getCreatedAt();
    }
}
