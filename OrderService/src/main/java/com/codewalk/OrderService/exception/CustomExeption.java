package com.codewalk.OrderService.exception;


import lombok.Data;

@Data
public class CustomExeption extends RuntimeException{

    private  int status;
    private String errorCode;
    private String message;


    public CustomExeption(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
