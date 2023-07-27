package de.iav.backend.controller;

import de.iav.backend.model.CurrentStockPrice;
import de.iav.backend.service.CurrentStockPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/financeapp/prices")
public class CurrentStockPriceController {
    private final CurrentStockPriceService currentStockPriceService;


    public CurrentStockPriceController(CurrentStockPriceService currentStockPriceService) {
        this.currentStockPriceService = currentStockPriceService;

    }

    @GetMapping
    public List<CurrentStockPrice> getAllCurrentStockPrice() {
        return currentStockPriceService.getAllCurrentStockPrice();
    }

    @GetMapping("/{stockTicker}")
    public Optional<CurrentStockPrice> getCurrentStockPriceByStockTicker(@PathVariable String stockTicker) {
        return currentStockPriceService.getCurrentStockPriceByStockTicker(stockTicker);
    }

    @GetMapping("/set")
    public List<CurrentStockPrice> setDefaultCurrentStockPrice() {
        return currentStockPriceService.setTempCurrentStockPriceByRepository();
    }

}
