package de.iav.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO {
    String typeOfTransaction;
    String dateAndTimeOfTransaction;

    User user;

    Stock stock;
    Integer quantity;
    Double price ;

    public Transaction getTransactionWithoutId() {
        return new Transaction(this.typeOfTransaction, this.dateAndTimeOfTransaction,this.user,this.stock,this.quantity,this.price);
    }
}
