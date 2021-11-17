package com.example.stock.controller;

import com.example.stock.model.Stock;
import com.example.stock.model.StockDto;
import com.example.stock.response.ApiResponse;
import com.example.stock.service.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Okala III
 */

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {

    @Autowired
    private StockServiceImpl stockService;

    private StockDto stockDto;

    @PostMapping()
    public ResponseEntity<ApiResponse> createStock(@RequestBody StockDto stockDto) {
        StockDto stock = stockService.createStock(stockDto);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK,"stock created successfully", stock));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateStock(@PathVariable Long id, @RequestBody StockDto stockDto) {
        stockDto = stockService.updateStock(id, stockDto);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK,"stock updated successfully", stockDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> findStock(@PathVariable long id) {
        StockDto stock = stockService.findStock(id);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK,"Item Retrieved successfully", stock));
    }


    @GetMapping()
    public ResponseEntity<ApiResponse> getAllStocksAtOnce() {
        List<StockDto> stocks = stockService.findAllStocks();
        String message = stocks.isEmpty() ?  "No Stocks Available" : "Stocks Retrieved successfully";

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK,message,stocks));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse>  deleteStock(@PathVariable long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK,"Item deleted successfully"));
    }

}