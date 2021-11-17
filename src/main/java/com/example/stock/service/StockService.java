package com.example.stock.service;

import com.example.stock.model.Stock;
import com.example.stock.model.StockDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Okala III
 */



public interface StockService {

    StockDto createStock(StockDto stockDto);

    StockDto findStock(long id);

    void deleteStock(long id);

    StockDto updateStock(Long id, StockDto stock);

    List<StockDto> findAllStocks();


}
