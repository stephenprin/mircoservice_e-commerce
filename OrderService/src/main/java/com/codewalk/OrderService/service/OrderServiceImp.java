package com.codewalk.OrderService.service;

import com.codewalk.OrderService.entity.Order;
import com.codewalk.OrderService.exception.CustomExeption;
import com.codewalk.OrderService.external.client.PaymentService;
import com.codewalk.OrderService.external.client.ProductService;
import com.codewalk.OrderService.external.request.PaymentRequest;
import com.codewalk.OrderService.model.OrderRequest;
import com.codewalk.OrderService.model.OrderResponse;
import com.codewalk.OrderService.repository.OrderRepository;
import com.codewalk.ProductService.model.ProductResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImp implements OrderService{

    private OrderRepository orderRepository;
    private ProductService productService;
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;

    public OrderServiceImp(OrderRepository orderRepository,
                           ProductService productService,
                           PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.paymentService = paymentService;
    }

    @Override
    public long placeOrder(OrderRequest orderRequest) {

          log.info("Placing order request", orderRequest);

          productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
          log.info("Created order request succesfully");
          Order order = Order.builder()
            .productId(orderRequest.getProductId())
            .amount(orderRequest.getTotalAmount())
            .quantity(orderRequest.getQuantity())
                  .orderDate(Instant.now())
                  .address(orderRequest.getAddress())
            .status("PLACED")
            .build();
         order=orderRepository.save(order);
        log.info("Calling payment service to do payment");

        System.out.println("order payment mode"+orderRequest.getPaymentMode().name());

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .amount(orderRequest.getTotalAmount())
                .paymentMode(orderRequest.getPaymentMode())
                .build();

        String orderStatus= null;
        try{
             paymentService.doPayment(paymentRequest);
             log.info("Payment successfully for order id: {}", order.getId());
            orderStatus="PLACED";
        }catch(Exception e){
            log.info("Payment failed for order id: {}", order.getId());
            orderStatus="PAYMENT_FAILED";
        }

        order.setStatus(orderStatus);
        orderRepository.save(order);
          log.info("Order placed successfully with order id: {}", order.getId());

            return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Fetching order details for order id: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new CustomExeption("Order not found", "NOT_FOUND", 404 ));
        log.info("Invoking product service to get product details for product id: {}", order.getProductId());
        ProductResponse productResponse = restTemplate.getForObject("http://product-service/product/" + order.getProductId(), ProductResponse.class);

        OrderResponse.ProductDetails productDetails= OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .price(productResponse.getPrice())
                .build();

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .amount(order.getAmount())
                .productDetails(productDetails)
                .build();
        log.info("Order details fetched successfully for order id: {}", orderId);
        return orderResponse;
    }
}
