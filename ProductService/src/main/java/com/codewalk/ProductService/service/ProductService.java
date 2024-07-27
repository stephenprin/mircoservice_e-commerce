package com.codewalk.ProductService.service;

import com.codewalk.ProductService.model.ProductRequest;
import com.codewalk.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long id);

    void reduceQuantity(long productId, long quantity);
}
