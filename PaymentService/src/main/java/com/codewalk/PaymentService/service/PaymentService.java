package com.codewalk.PaymentService.service;

import com.codewalk.PaymentService.model.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
