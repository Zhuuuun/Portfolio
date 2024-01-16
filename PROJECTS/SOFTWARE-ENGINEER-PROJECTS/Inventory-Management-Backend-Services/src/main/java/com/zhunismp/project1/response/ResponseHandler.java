package com.zhunismp.project1.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object responseObject) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        Map<String,Object> response = new HashMap<>();

        response.put("timeStamp",dtf.format(LocalDateTime.now()));
        response.put("message",message);
        response.put("httpStatus",httpStatus);
        response.put("data",responseObject);

        return new ResponseEntity<>(response,httpStatus);
    }
}
