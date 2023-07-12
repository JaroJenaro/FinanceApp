package de.iav.backend.controller;


import de.iav.backend.model.Transaction;
import de.iav.backend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/financeapp/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction executeTransactions(@RequestBody Transaction transaction) {
        return transactionService.executeTransaction(transaction);
    }


/*
    @GetMapping("/set")
    public List<Stock> getPetById(){
        return transactionService.setStockByRepository();
    }


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
