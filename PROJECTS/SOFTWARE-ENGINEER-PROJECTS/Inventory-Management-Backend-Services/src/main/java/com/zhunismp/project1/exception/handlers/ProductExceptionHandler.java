package com.zhunismp.project1.exception.handlers;

import com.zhunismp.project1.exception.ProductNotEnoughException;
import com.zhunismp.project1.exception.ProductNotFoundException;
import com.zhunismp.project1.response.ExceptionResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        // create payload
        ExceptionResponseHandler exceptionResponse = new ExceptionResponseHandler(
                productNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProductNotEnoughException.class})
    public ResponseEntity<Object> handleProductNotEnoughException(ProductNotEnoughException productNotEnoughExceptionException) {
        // create payload
        ExceptionResponseHandler exceptionResponse = new ExceptionResponseHandler(
                productNotEnoughExceptionException.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
