package de.iav.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "transactions")
public class Transaction {
        @MongoId
        String id;
        TransactionType typeOfTransaction;
        String dateAndTimeOfTransaction;
        @DBRef
        //UserWithoutUserDetails user;
        User user;
        @DBRef
        Stock stock;
        Integer quantity;
        Double price;

        public Transaction(TransactionType typeOfTransaction, String dateAndTimeOfTransaction, User user, Stock stock, Integer quantity, Double price) {
                this.typeOfTransaction = typeOfTransaction;
                this.dateAndTimeOfTransaction = dateAndTimeOfTransaction;
                this.user = user;
                this.stock = stock;
                this.quantity = quantity;
                this.price = price;
        }

        public TransactionWithEasyUser getTransactionWithEasyUser() {
                return new TransactionWithEasyUser(this.id, this.typeOfTransaction,
                        this.dateAndTimeOfTransaction, this.user.getUserWithoutUserDetails(),
                        this.stock, this.quantity, this.price);
        }

}
