package de.iav.backend.controller;


import de.iav.backend.externalAPICommunication.StockDataAccess;
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
    private final StockDataAccess stockDataAccess;


    public StockController(StockService stockService, StockDataAccess stockDataAccess) {
        this.stockService = stockService;
        this.stockDataAccess = stockDataAccess;
    }

    @GetMapping
    public List<Stock> getAllStocks(){return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Optional<Stock> getStockById(@PathVariable String id){
        return stockService.getStockById(id);
    }

    @GetMapping("/set")
    public List<Stock> setDefaultStocks() {
        return stockService.setStockByRepository();
    }

    @GetMapping("/price/{stockSymbol}")
    public Double getStockPrice(@PathVariable String stockSymbol){
        System.out.println(stockDataAccess.getLastPriceForStock(stockSymbol).toString());
        return stockDataAccess.getLastPriceForStock(stockSymbol);
    }


}
