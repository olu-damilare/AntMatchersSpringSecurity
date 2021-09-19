package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") Integer productId){
        return productService.getProduct(productId);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("{productId}")
    public void deleteProduct(@PathVariable("productId") Integer productId){
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "/{productId}")
    public Product updateProduct(
            @PathVariable Integer productId,
            @RequestParam(required =false) String productName,
            @RequestParam(required =false) String productDesc,
            @RequestParam(required =false) Double price

    ){
        return productService.updateProduct(productId, productName, productDesc, price);
    }


}
