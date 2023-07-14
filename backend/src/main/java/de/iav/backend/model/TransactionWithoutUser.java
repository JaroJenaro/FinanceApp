package de.iav.backend.model;

public record TransactionWithoutUser(
        String id,
        String typeOfTransaction,
        String dateAndTimeOfTransaction,
        Stock stock,
        Integer quantity,
        Double price
) {
}
