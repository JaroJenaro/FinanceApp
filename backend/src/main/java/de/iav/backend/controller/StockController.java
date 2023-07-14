package de.iav.backend.controller;


import de.iav.backend.model.Stock;
import de.iav.backend.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/financeapp/stocks")
public class StockController {

    private final StockService stockService;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getAllPets(){
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Optional<Stock> getStockById(@PathVariable String id){
        return stockService.getStockById(id);
    }

    @GetMapping("/set")
    public List<Stock> setDefaultStocks() {
        return stockService.setStockByRepository();
    }


}
