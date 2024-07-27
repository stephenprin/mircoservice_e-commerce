package com.codewalk.PaymentService.controller;

import com.codewalk.PaymentService.model.PaymentRequest;
import com.codewalk.PaymentService.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class ControllerPayment {
    private PaymentService paymentService;

    public ControllerPayment(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
            return new ResponseEntity<>(
                    paymentService.doPayment(paymentRequest),
                    HttpStatus.CREATED
            );
    }
}
