package de.iav.backend.service;

import de.iav.backend.model.Stock;
import de.iav.backend.model.Transaction;
import de.iav.backend.model.TransactionWithoutIdDTO;
import de.iav.backend.model.UserWithoutUserDetails;
import de.iav.backend.repository.TransactionRepository;
import de.iav.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    public final TransactionRepository transactionRepository;
    public final UserService userService;
    private final UserRepository userRepository;

    public List<Transaction> getAllTransactions() {
        System.out.println("get all transaction");
        System.out.println("get all transactions:  "+ transactionRepository.findAll());
        return transactionRepository.findAll();
    }


    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction executeTransaction(TransactionWithoutIdDTO transactionWithoutIdDTO) {
        Transaction transactionToSave = transactionWithoutIdDTO.getTransactionWithoutId();
        System.out.println(transactionToSave + "transactionToSave");
        Transaction executedTransaction = transactionRepository.save(transactionToSave);
        System.out.println("executedTransaction: " + executedTransaction);
        return executedTransaction;
    }

    public List<Stock> getAllStocksByUser(String id) {
        List<Stock> userStockList = new ArrayList<>();
        //List<Transaction> userTransactionList = transactionRepository.findAllByUserId(id);
        List<Transaction> userTransactionList = getAllTransactionsByUserId(id);
        System.out.println("transactionRepository.findAllByUserId(id).size: " + userTransactionList.size());
        System.out.println("userTransactionList: " + userTransactionList);
        for (Transaction transaction : userTransactionList) {
            System.out.println(transaction);
            if (transaction.getUser().getId().equals(id) && !userStockList.contains(transaction.getStock()))
                userStockList.add(transaction.getStock());
        }
        return userStockList;
    }


    public List<Transaction> getAllTransactionsByUserId(String userId) {
        UserWithoutUserDetails user = Objects.requireNonNull(userRepository.findById(userId).orElse(null)).getUserWithoutUserDetails();
        if (user == null) {
            // Handle the case when user is not found
            return null;
        }
        //return transactionRepository.findTransactionByUser(Optional.of(user));
        return transactionRepository.findTransactionByUser(user);
    }
    // public List<Transaction> getAllTransactionByUser(User user) {    }
}