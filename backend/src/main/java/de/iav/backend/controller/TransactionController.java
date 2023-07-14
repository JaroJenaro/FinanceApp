package de.iav.backend.controller;


import de.iav.backend.model.Stock;
import de.iav.backend.model.Transaction;
import de.iav.backend.model.User;
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

    /*   @GetMapping
       public List<Stock> getTransactionById(@RequestBody User user) {
           return transactionService.getAllStocksByUser(user);
       }

     */
    @GetMapping("/user")
    public List<Stock> getAllStocksByUser(@RequestBody User user) {
        return transactionService.getAllStocksByUser(user);
    }
 /*
    @GetMapping("/usert")
    public List<Transaction> getAllTransactionByUser(@RequestBody User user) {
        return transactionService.getAllTransactionByUser(user);
    }
*/
}
