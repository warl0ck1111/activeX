package com.example.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.stock.exception.StockNotFoundException;
import com.example.stock.model.Stock;
import com.example.stock.model.StockDto;
import com.example.stock.repository.StockRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {StockServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StockServiceImplTest {
    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockServiceImpl stockServiceImpl;

    @Test
    void testCreateStock() {
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.createStock(new StockDto()));
    }

    @Test
    void testCreateStock2() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock);
        StockDto stockDto = new StockDto("Name", 10.0);

        StockDto actualCreateStockResult = this.stockServiceImpl.createStock(stockDto);
        assertSame(stockDto, actualCreateStockResult);
        assertEquals(10.0, actualCreateStockResult.getCurrentPrice().doubleValue());
        assertEquals(1, actualCreateStockResult.getQuantity());
        assertEquals("Name", actualCreateStockResult.getName());
        verify(this.stockRepository).save((Stock) any());
        assertTrue(this.stockServiceImpl.findAllStocks().isEmpty());
    }

    @Test
    void testCreateStock3() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock);
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.createStock(new StockDto("", 10.0)));
    }

    @Test
    void testCreateStock4() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock);
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.createStock(new StockDto("Name", -0.5)));
    }

    @Test
    void testCreateStock5() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock);
        StockDto stockDto = mock(StockDto.class);
        doNothing().when(stockDto).setQuantity(anyInt());
        doNothing().when(stockDto).setName((String) any());
        doNothing().when(stockDto).setCurrentPrice((Double) any());
        when(stockDto.getQuantity()).thenReturn(1);
        when(stockDto.getCurrentPrice()).thenReturn(10.0);
        when(stockDto.getName()).thenReturn("Name");
        this.stockServiceImpl.createStock(stockDto);
        verify(this.stockRepository).save((Stock) any());
        verify(stockDto, atLeast(1)).getCurrentPrice();
        verify(stockDto, atLeast(1)).getName();
        verify(stockDto).getQuantity();
        verify(stockDto).setCurrentPrice((Double) any());
        verify(stockDto).setName((String) any());
        verify(stockDto).setQuantity(anyInt());
        assertTrue(this.stockServiceImpl.findAllStocks().isEmpty());
    }

    @Test
    void testFindStock() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Stock> ofResult = Optional.<Stock>of(stock);
        when(this.stockRepository.findById((Long) any())).thenReturn(ofResult);
        StockDto actualFindStockResult = this.stockServiceImpl.findStock(123L);
        assertEquals(10.0, actualFindStockResult.getCurrentPrice().doubleValue());
        assertEquals(1, actualFindStockResult.getQuantity());
        assertEquals("Name", actualFindStockResult.getName());
        verify(this.stockRepository).findById((Long) any());
        assertTrue(this.stockServiceImpl.findAllStocks().isEmpty());
    }

    @Test
    void testFindStock2() {
        when(this.stockRepository.findById((Long) any())).thenReturn(Optional.<Stock>empty());
        assertThrows(StockNotFoundException.class, () -> this.stockServiceImpl.findStock(123L));
        verify(this.stockRepository).findById((Long) any());
    }

    @Test
    void testFindStock3() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Stock> ofResult = Optional.<Stock>of(stock);
        when(this.stockRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.findStock(0L));
    }

    @Test
    void testDeleteStock() {
        doNothing().when(this.stockRepository).deleteById((Long) any());
        this.stockServiceImpl.deleteStock(123L);
        verify(this.stockRepository).deleteById((Long) any());
        assertTrue(this.stockServiceImpl.findAllStocks().isEmpty());
    }

    @Test
    void testDeleteStock2() {
        doNothing().when(this.stockRepository).deleteById((Long) any());
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.deleteStock(0L));
    }

    @Test
    void testUpdateStock() {
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.updateStock(123L, new StockDto()));
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.updateStock(0L, new StockDto()));
    }

    @Test
    void testUpdateStock2() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Stock> ofResult = Optional.<Stock>of(stock);

        Stock stock1 = new Stock();
        stock1.setCurrentPrice(10.0);
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock1.setQuantity(1);
        stock1.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock1);
        when(this.stockRepository.findById((Long) any())).thenReturn(ofResult);
        StockDto stockDto = new StockDto("Name", 10.0);

        StockDto actualUpdateStockResult = this.stockServiceImpl.updateStock(123L, stockDto);
        assertSame(stockDto, actualUpdateStockResult);
        assertEquals(10.0, actualUpdateStockResult.getCurrentPrice().doubleValue());
        assertEquals(1, actualUpdateStockResult.getQuantity());
        assertEquals("Name", actualUpdateStockResult.getName());
        verify(this.stockRepository).findById((Long) any());
        verify(this.stockRepository).save((Stock) any());
        assertTrue(this.stockServiceImpl.findAllStocks().isEmpty());
    }

    @Test
    void testUpdateStock3() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock);
        when(this.stockRepository.findById((Long) any())).thenReturn(Optional.<Stock>empty());
        assertThrows(StockNotFoundException.class,
                () -> this.stockServiceImpl.updateStock(123L, new StockDto("Name", 10.0)));
        verify(this.stockRepository).findById((Long) any());
    }

    @Test
    void testUpdateStock4() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Stock> ofResult = Optional.<Stock>of(stock);

        Stock stock1 = new Stock();
        stock1.setCurrentPrice(10.0);
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock1.setQuantity(1);
        stock1.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock1);
        when(this.stockRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> this.stockServiceImpl.updateStock(123L, new StockDto("", 10.0)));
    }

    @Test
    void testUpdateStock5() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Stock> ofResult = Optional.<Stock>of(stock);

        Stock stock1 = new Stock();
        stock1.setCurrentPrice(10.0);
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock1.setQuantity(1);
        stock1.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock1);
        when(this.stockRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> this.stockServiceImpl.updateStock(123L, new StockDto("Name", -0.5)));
    }

    @Test
    void testUpdateStock6() {
        Stock stock = new Stock();
        stock.setCurrentPrice(10.0);
        stock.setId(123L);
        stock.setName("Name");
        stock.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        stock.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Stock> ofResult = Optional.<Stock>of(stock);

        Stock stock1 = new Stock();
        stock1.setCurrentPrice(10.0);
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setLastUpdated(LocalDateTime.of(1, 1, 1, 1, 1));
        stock1.setQuantity(1);
        stock1.setDateCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.stockRepository.save((Stock) any())).thenReturn(stock1);
        when(this.stockRepository.findById((Long) any())).thenReturn(ofResult);
        StockDto stockDto = mock(StockDto.class);
        doNothing().when(stockDto).setQuantity(anyInt());
        doNothing().when(stockDto).setName((String) any());
        doNothing().when(stockDto).setCurrentPrice((Double) any());
        when(stockDto.getQuantity()).thenReturn(1);
        when(stockDto.getCurrentPrice()).thenReturn(10.0);
        when(stockDto.getName()).thenReturn("Name");
        this.stockServiceImpl.updateStock(123L, stockDto);
        verify(this.stockRepository).findById((Long) any());
        verify(this.stockRepository).save((Stock) any());
        verify(stockDto, atLeast(1)).getCurrentPrice();
        verify(stockDto, atLeast(1)).getName();
        verify(stockDto).getQuantity();
        verify(stockDto).setCurrentPrice((Double) any());
        verify(stockDto).setName((String) any());
        verify(stockDto).setQuantity(anyInt());
        assertTrue(this.stockServiceImpl.findAllStocks().isEmpty());
    }

    @Test
    void testFindAllStocks() {
        ArrayList<Stock> stockList = new ArrayList<Stock>();
        when(this.stockRepository.findAll()).thenReturn(stockList);
        List<Stock> actualFindAllStocksResult = this.stockServiceImpl.findAllStocks();
        assertSame(stockList, actualFindAllStocksResult);
        assertTrue(actualFindAllStocksResult.isEmpty());
        verify(this.stockRepository).findAll();
    }
}

