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
@AllArgsConstructor
public class StockDto {

    private String name;

    private Double currentPrice;


}
