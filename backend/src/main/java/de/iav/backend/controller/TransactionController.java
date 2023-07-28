package de.iav.backend.controller;


import de.iav.backend.model.Stock;
import de.iav.backend.model.Transaction;
import de.iav.backend.model.TransactionWithEasyUser;
import de.iav.backend.model.TransactionWithoutIdDTO;
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
    public TransactionWithEasyUser executeTransactions(@RequestBody TransactionWithoutIdDTO transactionWithoutIdDTO) {
        System.out.println(transactionWithoutIdDTO.toString() + "transactionDT");
        return transactionService.executeTransaction(transactionWithoutIdDTO);
    }

    @GetMapping("/stocks/user/{id}")
    public List<Stock> getAllStocksByUser(@PathVariable String id) {

        return transactionService.getAllStocksByUser(id);
    }

    @GetMapping("/user/{id}")
    public List<TransactionWithEasyUser> getAllEasyTransactionByUser(@PathVariable String id) {
        return transactionService.getAllEasyTransactionsByUserId(id);
    }


}