package com.zhunismp.project1.exception.handlers;

import com.zhunismp.project1.exception.CustomerNotFoundException;
import com.zhunismp.project1.response.ExceptionResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<Object> handleOrderNotFoundException(CustomerNotFoundException customerNotFoundException) {
        // create payload
        ExceptionResponseHandler exceptionResponse = new ExceptionResponseHandler(
                customerNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
