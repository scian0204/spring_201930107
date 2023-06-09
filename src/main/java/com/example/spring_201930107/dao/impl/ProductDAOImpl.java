package com.example.spring_201930107.dao.impl;

import com.example.spring_201930107.dao.ProductDAO;
import com.example.spring_201930107.entity.Product;
import com.example.spring_201930107.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws Exception {
        Optional<Product> productOptional = productRepository.findById(product.getNumber());
        Product updatedProduct;
        if (productOptional.isPresent()) {
            Product productGet = productOptional.get();
            product.setCreatedAt(productGet.getCreatedAt());
            updatedProduct = productRepository.save(product);
        } else throw new Exception();

        return updatedProduct;
    }

    @Override
    public void deleteProduct(long number) throws Exception{
        Optional<Product> selectedProduct = productRepository.findById(number);
        if (selectedProduct.isPresent()) {
            productRepository.delete(selectedProduct.get());
        } else throw new Exception();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllBy();
    }

    @Override
    public List<Product> getAllProductsOrderByPrice() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product getProductByNumber(Long number) {
        Optional<Product> productOptional = productRepository.findById(number);
        return productOptional.isPresent() ? productOptional.get() : null;
    }
}
