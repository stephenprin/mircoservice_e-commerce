package com.codewalk.OrderService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "product_id", nullable = false)
    private long productId;
    @Column(name = "quantity")
    private long quantity;
    @Column(name = "total_amount")
    private double amount;
    @Column(name = "order_status")
    private String status;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "order_date")
    private Instant orderDate;


}
