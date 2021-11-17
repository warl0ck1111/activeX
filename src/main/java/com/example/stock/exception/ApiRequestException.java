package com.example.stock.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiRequestException extends RuntimeException{

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
