package de.iav.backend.service;

import de.iav.backend.exception.UserNotFoundException;
import de.iav.backend.model.Stock;
import de.iav.backend.model.Transaction;
import de.iav.backend.model.User;
import de.iav.backend.repository.StockRepository;
import de.iav.backend.repository.TransactionRepository;
import de.iav.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    public final List<User> tempUsers = new ArrayList<>(Arrays.asList(
            new User("12345", "Erum", "Schuakat", "erum.schaukat@iav.de", "12345"),
            new User("23456", "Houman", "Mohammadi", "houman.mohammadi@iav.de", "23456"),
            new User("34567", "Jaroslaw", "Placzek", "jaroslaw.placzek@iav.de", "34567")
    ));
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final StockRepository stockRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Transaction> getAllTransactionsByUser(Optional<User> user){
        return transactionRepository.findTransactionByUser(user);
    }
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(String id, User userToUpdate) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        User updatedStudent = userToUpdate.withId(id);
        return userRepository.save(updatedStudent);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> setUserByRepository() {
        if (userRepository.findAll().size() == 0)
            return fillDataWithUsers();
        else
            return userRepository.findAll();
    }

    private List<User> fillDataWithUsers() {
        return userRepository.saveAll(tempUsers);
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public List<Transaction> calculateOwnedStocks(String userId) {
        List<Transaction> portfolioList = transactionRepository.findAllByUserId(userId);
        Map<String, Integer> stockQuantities = portfolioList.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getStock().getStockTicker(),
                        Collectors.summingInt(Transaction::getQuantity)
                ));

        return stockQuantities.entrySet().stream()
                .map(entry -> {
                    String stockTicker = entry.getKey();
                    Integer quantity = entry.getValue();

                    Optional<Stock> stockInfo = stockRepository.findStocksByStockTicker(stockTicker);
                    String companyName = stockInfo.map(Stock::getCompanyName).orElse(null);


                    return portfolioList.stream()
                            .filter(transaction -> transaction.getStock().getStockTicker().equals(stockTicker))
                            .findFirst()
                            .map(transaction -> new Transaction(
                                    transaction.getId(),
                                    transaction.getTypeOfTransaction(),
                                    transaction.getDateAndTimeOfTransaction(),
                                    transaction.getUser(),
                                    new Stock(stockTicker, companyName, null, null),
                                    quantity,
                                    transaction.getPrice()
                            ))
                            .orElse(null);
                })
                .collect(Collectors.toList());
    }
}