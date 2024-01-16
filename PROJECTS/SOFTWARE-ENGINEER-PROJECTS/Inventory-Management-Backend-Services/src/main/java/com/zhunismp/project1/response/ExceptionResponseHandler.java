package com.zhunismp.project1.response;

import org.springframework.http.HttpStatus;

public class ExceptionResponseHandler {
    private final String message;
    private final HttpStatus httpStatus;

    public ExceptionResponseHandler(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
