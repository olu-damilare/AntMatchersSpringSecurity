package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Integer productId;
    private String name;
    private String description;
    private Double price;

    public Product(Integer productId, String name, String desc, Double price){
        this(name, desc, price);
        this.productId = productId;

    }

    public Product(){

    }

    public Product(String name, String desc, Double price) {
        this.name = name;
        this.description = desc;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getProductId() {
        return productId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
