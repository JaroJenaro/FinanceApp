package de.iav.frontend.model;

public record TransactionWithoutUser(
        String id,
        TransactionType typeOfTransaction,
        String dateAndTimeOfTransaction,
        Stock stock,
        Integer quantity,
        Double price
) {
}
