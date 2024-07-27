package com.codewalk.ProductService.service;

import com.codewalk.ProductService.entity.Product;
import com.codewalk.ProductService.exception.ProductServiceCustomException;
import com.codewalk.ProductService.model.ProductRequest;
import com.codewalk.ProductService.model.ProductResponse;
import com.codewalk.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        product=productRepository.save(product);
        log.info("Product added successfully", product.getId());
        return product.getId();

    }

    @Override
    public ProductResponse getProductById(long id) {
        log.info("Fetching product by id");
        Product product = productRepository.findById(id).orElseThrow(()->new ProductServiceCustomException("Product not found", "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse =  new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        log.info("Product fetched successfully");
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reducing quantity of product");
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductServiceCustomException("Product not found", "PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException("Quantity not available", "QUANTITY_NOT_AVAILABLE");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Quantity reduced successfully");
    }

}
