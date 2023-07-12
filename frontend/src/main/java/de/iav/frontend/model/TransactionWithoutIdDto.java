package de.iav.frontend.model;

public record TransactionWithoutIdDto(TransactionType typeOfTransaction,
                                      String dateAndTimeOfTransaction,
                                      User user,
                                      Stock stock,
                                      Integer quantity,
                                      Double price) {
}
