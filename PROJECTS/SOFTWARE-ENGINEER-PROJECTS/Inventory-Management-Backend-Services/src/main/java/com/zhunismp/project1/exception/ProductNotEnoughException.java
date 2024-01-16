package com.zhunismp.project1.exception;

public class ProductNotEnoughException extends RuntimeException{
    public ProductNotEnoughException(String message) {
        super(message);
    }

    public ProductNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}
