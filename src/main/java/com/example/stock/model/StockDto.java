package com.example.stock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Okala III
 */

@Data
@NoArgsConstructor
public class StockDto {

    private String name;

    private Double currentPrice;

    private int quantity;

    public StockDto(String name, Double currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public StockDto(String name, Double currentPrice, int quantity) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
    }
}
