package com.example.stock.service;

import com.example.stock.exception.StockNotFoundException;
import com.example.stock.model.Stock;
import com.example.stock.model.StockDto;
import com.example.stock.repository.StockRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.stock.util.Util.hasValue;

/**
 * @author Okala III
 */

@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    private Stock stock;
    private StockDto stockDto;


    @Override
    public StockDto createStock(StockDto stockDto) {
        if (!hasValue(stockDto.getName())) {
            log.error("Stock name cannot be empty");
            throw new IllegalArgumentException("Stock name cannot be empty");
        }

        if (stockDto.getCurrentPrice() < 0) {
            log.error("Invalid current price");
            throw new IllegalArgumentException("Invalid current price");
        }

        stock = new Stock();
        BeanUtils.copyProperties(stockDto,stock);

        Stock savedStock = stockRepository.save(stock);
        BeanUtils.copyProperties(savedStock,stockDto);

        return stockDto;
    }

    @Override
    public StockDto findStock(long id) {
        if(id <= 0) throw new IllegalArgumentException("invalid stock id");

        stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException("no stock found"));
        BeanUtils.copyProperties(stock, stockDto);

        return stockDto;
    }

    @Override
    public void deleteStock(long id) {
        if(id <= 0) throw new IllegalArgumentException("invalid stock id");
        stockRepository.deleteById(id);
    }

    @Override
    public StockDto updateStock(Long id, StockDto stockDto) {
        if(id <= 0) throw new IllegalArgumentException("invalid stock id");

        if (!hasValue(stockDto.getName())) {
            log.error("Stock name cannot be empty");
            throw new IllegalArgumentException("Stock name cannot be empty");
        }

        if (stockDto.getCurrentPrice() < 0) {
            log.error("Invalid current price");
            throw new IllegalArgumentException("Invalid current price");
        }

        stockRepository.findById(id).orElseThrow(()-> new StockNotFoundException("no stock found"));
        BeanUtils.copyProperties(stockDto,stock);

        Stock savedStock = stockRepository.save(stock);
        BeanUtils.copyProperties(savedStock,stockDto);

        return stockDto;
    }

    @Override
    public List<StockDto> findAllStocks() {
        List<StockDto> stockDtoList = new ArrayList<>();
        stockRepository.findAll().forEach((stock)->{
            BeanUtils.copyProperties(stock, stockDto);
            stockDtoList.add(stockDto);
        });
        return stockDtoList;
    }

    public Page<Stock> findAllStocks(int page, int size){
//        List<StockDto> stockDtoList = new ArrayList<>();
        return stockRepository.findAll(PageRequest.of(page,size));
//        return  stockDtoList;
    }
}
