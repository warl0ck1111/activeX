package com.example.stock.exception;

/**
 * @author Okala III
 */
public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String message) {
        super(message);
    }
}
