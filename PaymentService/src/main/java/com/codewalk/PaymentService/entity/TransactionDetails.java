package com.codewalk.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "transaction_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "orderId")
    private Long orderId;
    @Column(name = "paymentMode")
    private String paymentMode;
    @Column(name = "reference_number")
    private String referenceNumber;
    @Column(name = "payment_date")
    private Instant paymentDate;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "status")
    private String status;



}
