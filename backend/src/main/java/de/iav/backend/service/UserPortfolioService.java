package de.iav.backend.service;

import de.iav.backend.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserPortfolioService {
    public final TransactionService transactionService;
    public final CurrentStockPriceService currentStockPriceService;

    public List<UserPortfolio> makeUserPortfolio(String userId) {
        List<Transaction> userTransactions = transactionService.transactionRepository.findAllByUserId(userId);

        // Use a Map to group transactions based on the stock
        Map<String, List<Transaction>> transactionsByStock = new HashMap<>();
        for (Transaction transaction : userTransactions) {
            String stockId = transaction.getStock().getStockTicker();
            transactionsByStock.computeIfAbsent(stockId, key -> new ArrayList<>()).add(transaction);
        }

        List<UserPortfolio> userPortfolio = new ArrayList<>();

        // Iterate over the transactions grouped by stock and calculate portfolio for each stock
        for (Map.Entry<String, List<Transaction>> entry : transactionsByStock.entrySet()) {
            String stockId = entry.getKey();
            List<Transaction> stockTransactions = entry.getValue();

            Stock stock = stockTransactions.get(0).getStock();
            int totalQuantity = 0;
            double totalPrice = 0.0;

            for (Transaction transaction : stockTransactions) {
                int quantity = transaction.getQuantity();
                double price = transaction.getPrice();
                totalQuantity += transaction.getTypeOfTransaction() == TransactionType.BUY ? quantity : -quantity; // Subtract if SELL
                totalPrice += transaction.getTypeOfTransaction() == TransactionType.BUY ? quantity * price : -quantity * price; // Subtract if SELL
            }

            // Calculate average buy price
            double averageBuyPrice = totalQuantity == 0 ? 0 : totalPrice / totalQuantity;

            // Fetch the current price for the stock using some API or external data source
            Optional<CurrentStockPrice> currentPrice = currentStockPriceService.getCurrentStockPriceByStockTicker(stock.getStockTicker());

            double currentValue = 0;
            double performance = 0;

            if (currentPrice.isPresent()) {
                CurrentStockPrice currentStockPrice = currentPrice.get();
                currentValue = currentStockPrice.getPrice() * totalQuantity;
                performance = currentValue - totalPrice;
            } else {
                System.out.println("Current Price is not available.");
            }
            double portfolioValue = userPortfolio.stream()
                    .mapToDouble(portfolio -> portfolio.currentPrice() * portfolio.quantity())
                    .sum();

            System.out.println("Portfolio Value: " + portfolioValue);

            // Build UserPortfolio Object with aggregated data for each stock
            UserPortfolio stockPortfolio = new UserPortfolio(stock, totalQuantity, averageBuyPrice, currentValue, performance, portfolioValue);
            userPortfolio.add(stockPortfolio);
        }

        return userPortfolio;
    }

}
