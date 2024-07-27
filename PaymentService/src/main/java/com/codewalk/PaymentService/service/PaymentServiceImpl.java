package com.codewalk.PaymentService.service;

import com.codewalk.PaymentService.entity.TransactionDetails;
import com.codewalk.PaymentService.model.PaymentRequest;
import com.codewalk.PaymentService.repository.TransactionsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    private TransactionsRepository transactionsRepository;

    public PaymentServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }


    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Payment details processing:", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .status("SUCCESS")
                .referenceNumber(paymentRequest.getReferenceNumber())
                .build();

        transactionsRepository.save(transactionDetails);
        log.info("Payment details saved successfully with id :{}", transactionDetails.getId());

        return transactionDetails.getId();
    }
}
