package de.iav.backend.service;

import de.iav.backend.model.Stock;
import de.iav.backend.model.Transaction;
import de.iav.backend.model.User;
import de.iav.backend.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    public final TransactionRepository transactionRepository;
    public final UserService userService;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction executeTransaction(Transaction transaction) {
        Transaction executedTransaction = transactionRepository.save(transaction);
        System.out.println("executedTransaction: " + executedTransaction);
        userService.updateUserTransaction(executedTransaction);
        return executedTransaction;
    }

    public List<Stock> getAllStocksByUser(User user) {
        return transactionRepository.findAllByUser(user);
    }

    // public List<Transaction> getAllTransactionByUser(User user) {    }
}