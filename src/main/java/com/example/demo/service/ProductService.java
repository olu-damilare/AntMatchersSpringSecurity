package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProduct(Integer productId);
    void deleteProduct(Integer productId);
    Product updateProduct(Integer productId, String productName, String productDescription, Double price);
}
