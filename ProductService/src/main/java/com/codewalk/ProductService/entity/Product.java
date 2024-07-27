package com.codewalk.ProductService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId", nullable = false)
    private long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private long price;
    @Column(name = "product_quantity")
    private long quantity;

}
