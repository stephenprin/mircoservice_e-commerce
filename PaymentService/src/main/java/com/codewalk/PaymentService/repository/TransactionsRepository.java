package com.codewalk.PaymentService.repository;

import com.codewalk.PaymentService.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionDetails, Long>{
}
