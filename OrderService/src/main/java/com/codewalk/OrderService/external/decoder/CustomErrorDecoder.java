package com.codewalk.OrderService.external.decoder;

import com.codewalk.OrderService.exception.CustomExeption;
import com.codewalk.OrderService.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder{
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Error response from server: {}", response.request().url());
        log.info("Error response from server: {}", response.request().headers());

        try {
            ErrorResponse  errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new CustomExeption(errorResponse.getMessage(), errorResponse.getErrorCode(), response.status());
        } catch (IOException e) {
            throw new CustomExeption("Internal server error", "INTERNAL_SERVER_ERROR", 500);
        }
    }
}
