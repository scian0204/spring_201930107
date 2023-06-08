package com.example.spring_201930107.controller;

import com.example.spring_201930107.dto.ProductRequestDto;
import com.example.spring_201930107.dto.ProductResponseDto;
import com.example.spring_201930107.dto.ProductUpdateDto;
import com.example.spring_201930107.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Operation(summary = "상품 수정 - 상품명, 가격, 재고 수정 가능, ADMIN 만")
    @PutMapping("")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductUpdateDto productUpdateDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productUpdateDto));
    }

    @Operation(summary = "상품 등록 - ADMIN 만")
    @PostMapping("")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProduct(productRequestDto));
    }

    @Operation(summary = "상품 삭제 - ADMIN 만")
    @DeleteMapping("")
    public ResponseEntity<String> deleteProduct(@RequestParam long number) throws Exception{
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @Operation(summary = "상품 리스트 - 누구나 볼 수 있음")
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @Operation(summary = "상품 리스트 (가격순으로 : 내림차순) - 누구나 볼 수 있음")
    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsOrderByPrice() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductsOrderByPrice());
    }

    @Operation(summary = "상품 리스트 - 이름을 통해 가져오기 (이름은 중복 가능), 누구나 볼 수 있음")
    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> getProductsByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByName(name));
    }

    @Operation(summary = "상품 정보 - 아이디를 통해 가져오기")
    @GetMapping("/")
    public ResponseEntity<ProductResponseDto> getProductByNumber(@RequestParam Long number) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByNumber(number));
    }

}
