package de.iav.frontend.model;

public record Transaction(
        String id,
        TransactionType typeOfTransaction,
        String dateAndTimeOfTransaction,
        User user,
        Stock stock,
        Integer quantity,
        Double price) {
}
