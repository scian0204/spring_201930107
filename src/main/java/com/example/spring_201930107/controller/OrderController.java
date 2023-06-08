package com.example.spring_201930107.controller;

import com.example.spring_201930107.dto.OrderRequestDto;
import com.example.spring_201930107.dto.OrderResponseDto;
import com.example.spring_201930107.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Operation(summary = "주문(구매) 등록 - USER 만 등록")
    @PostMapping("")
    public ResponseEntity<OrderResponseDto> buy(@RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.buy(orderRequestDto, token));
    }

    @Operation(summary = "주문 리스트 - ADMIN 만 볼 수 있음")
    @GetMapping("/list")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }
    @Operation(summary = "상품별 주문 리스트 (구매자 아이디를 통해) - ADMIN 만 볼 수 있음")
    @GetMapping("/listByUserId")
    public ResponseEntity<List<OrderResponseDto>> getAllOrdersByUserId(@RequestParam String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrdersByUserId(userId));
    }
    @Operation(summary = "상품별 주문 리스트 (상품 아이디를 통해) - ADMIN 만 볼 수 있음")
    @GetMapping("/listByProductId")
    public ResponseEntity<List<OrderResponseDto>> getAllOrdersByProductId(@RequestParam String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrdersByProductId(productId));
    }
    @Operation(summary = "주문 정보 - 아이디를 통해 가져오기, ADMIN 만 볼 수 있음")
    @GetMapping("/")
    public ResponseEntity<OrderResponseDto> getOrderById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
    }
}
