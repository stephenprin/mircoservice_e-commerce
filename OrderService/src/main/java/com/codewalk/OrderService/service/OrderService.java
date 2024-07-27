package com.codewalk.OrderService.service;

import com.codewalk.OrderService.model.OrderRequest;
import com.codewalk.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
