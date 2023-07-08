package de.iav.backend.controller;


import de.iav.backend.model.Stock;
import de.iav.backend.service.StockService;
import org.springframework.web.bind.annotation.*;

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
    public List<Stock> getPetById(){
        return stockService.setStockByRepository();
    }

    /*
    @GetMapping("/search")
    public List<User> getPetsBySpecificSearch(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName ){

        if(firstName != null)
        {
            return stockService.findAllByFirstNameEqualsIgnoreCase(firstName);
        }
        else{
            return stockService.findAllByLastNameEqualsIgnoreCase(lastName);
        }
    }*/
}
