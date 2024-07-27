package com.codewalk.OrderService.exception;

import com.codewalk.OrderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomExeption.class)
    public ResponseEntity<ErrorResponse> handleCustomServiceCustomException(CustomExeption ex) {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .build(),
                HttpStatus.valueOf(ex.getStatus()));


    }


}
