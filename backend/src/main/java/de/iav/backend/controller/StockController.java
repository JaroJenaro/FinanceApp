package de.iav.backend.controller;


import de.iav.backend.externalAPICommunication.StockDataAccess;
import de.iav.backend.model.CurrentStockPrice;
import de.iav.backend.model.Stock;
import de.iav.backend.service.CurrentStockPriceService;
import de.iav.backend.service.StockService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/financeapp/stocks")
public class StockController implements ApplicationRunner {

    private final StockService stockService;
    private final StockDataAccess stockDataAccess;
    private final CurrentStockPriceService currentStockPriceService;


    public StockController(StockService stockService, StockDataAccess stockDataAccess, CurrentStockPriceService currentStockPriceService) {
        this.stockService = stockService;
        this.stockDataAccess = stockDataAccess;
        this.currentStockPriceService = currentStockPriceService;
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Optional<Stock> getStockById(@PathVariable String id) {
        return stockService.getStockById(id);
    }

    @GetMapping("/set")
    public List<Stock> setDefaultStocks() {
        return stockService.setStockByRepository();
    }



    @GetMapping("/price/{stockSymbol}")
    public Double getStockPrice(@PathVariable String stockSymbol) {
        System.out.println(stockDataAccess.getLastPriceForStock(stockSymbol).toString());
        Double currentPrice = stockDataAccess.getLastPriceForStock(stockSymbol);
        CurrentStockPrice currentStockPrice = new CurrentStockPrice(stockSymbol, currentPrice, LocalDateTime.now().toString());
        currentStockPriceService.saveCurrentStockPrice(currentStockPrice);
        return currentPrice;
    }

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        Thread.sleep(5000);
        setAllStockPrices();
    }

    @GetMapping("/setAllPrice")
    public void setAllStockPrices() throws InterruptedException {
        for (Stock stock : getAllStocks()) {
            try {
                System.out.println(stockDataAccess.getLastPriceForStock(stock.getStockTicker()).toString());
                Double currentPrice = stockDataAccess.getLastPriceForStock(stock.getStockTicker());
                CurrentStockPrice currentStockPrice = new CurrentStockPrice(stock.getStockTicker(), currentPrice, LocalDateTime.now().toString());
                currentStockPriceService.saveCurrentStockPrice(currentStockPrice);
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                // Handle the exception here (e.g., log the exception)
                e.printStackTrace();
                // Optionally, you can throw a custom unchecked exception to signal the interruption.
                // throw new RuntimeException("Thread was interrupted while sleeping.", e);
            }
        }
    }
}

