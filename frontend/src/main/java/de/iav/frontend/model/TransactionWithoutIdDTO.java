package de.iav.frontend.model;

public record TransactionWithoutIdDTO(

        TransactionType typeOfTransaction,
        String dateAndTimeOfTransaction,
        User user,
        Stock stock,
        Integer quantity,
        Double price) {
}
