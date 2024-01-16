package com.zhunismp.project1.exception.handlers;

import com.zhunismp.project1.exception.OrderNotFoundException;
import com.zhunismp.project1.response.ExceptionResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

// @ControllerAdvice is to help manage,route exception in background
@ControllerAdvice
public class OrderExceptionHandler {

    // value is which class (Exception) is this method handle
    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException) {
        // create payload
        ExceptionResponseHandler exceptionResponse = new ExceptionResponseHandler(
          orderNotFoundException.getMessage(),
          HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Object> handleOrderIntegrityConstraintException(SQLIntegrityConstraintViolationException e) {
        ExceptionResponseHandler exceptionResponse = new ExceptionResponseHandler(
                "Customer id / Product id might be invalid",
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
