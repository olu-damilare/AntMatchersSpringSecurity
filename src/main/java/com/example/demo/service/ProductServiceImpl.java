package com.example.demo.service;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id"));
    }

    @Override
    public void deleteProduct(Integer productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Integer productId, String productName, String productDescription, Double price) {


        Product product = getProduct(productId);
        boolean emptyName = productName == null || productName.length() < 1;
        boolean emptyProductDesc = productDescription == null || productDescription.length() < 1;
        boolean validPrice = price != null && (price.compareTo((double) 0) > 0);

        if (!emptyName && !product.getName().equals(productName)) {
            product.setName(productName);
        }
        if (!emptyProductDesc && !product.getDescription().equals(productDescription)) {
          product.setDescription(productDescription);
        }
        if(validPrice){
            product.setPrice(price);
        }

        productRepository.save(product);

        return product;
    }
}
