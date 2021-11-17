package com.example.stock.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {StockNotFoundException.class})
    public ResponseEntity<Object> StockNotFoundExceptionHandler(ApiRequestException ex){
        log.info(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, ex.getHttpStatus(), LocalDateTime.now());
        return new ResponseEntity<>(apiException, ex.getHttpStatus() );

    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> IllegalArgumentExceptionHandler(Exception ex){
        log.info(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

    }


}
