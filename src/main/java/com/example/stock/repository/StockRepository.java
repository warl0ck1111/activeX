package com.example.stock.repository;

import com.example.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Okala III
 */


@RestController
public interface StockRepository extends JpaRepository<Stock,Long> {

}
