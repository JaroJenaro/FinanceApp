package de.iav.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class TransactionWithEasyUser {

    String id;
    TransactionType typeOfTransaction;
    String dateAndTimeOfTransaction;
    UserWithoutUserDetails user;
    Stock stock;
    Integer quantity;
    Double price;
}
