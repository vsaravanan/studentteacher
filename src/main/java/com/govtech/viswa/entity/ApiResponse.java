package com.govtech.viswa.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private Integer records;
    private Object result;

    public ApiResponse(HttpStatus status, String message, Integer records, Object result) {
        this.status = status;
        this.message = message;
        this.records = records;
        this.result = result;
    }

}
