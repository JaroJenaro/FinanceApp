package de.iav.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
        String typeOfTransaction;
        String dateAndTimeOfTransaction;
        @DBRef
        User user;
        @DBRef
        Stock stock;
        Integer quantity;
        Double price ;

        public Transaction(String typeOfTransaction, String dateAndTimeOfTransaction, User user, Stock stock, Integer quantity, Double price) {
                this.typeOfTransaction=typeOfTransaction;
                this.dateAndTimeOfTransaction=dateAndTimeOfTransaction;
                this.user=user;
                this.stock=stock;
                this.quantity=quantity;
                this.price=price;
        }

/*        public Transaction getTransactionWithoutId(){
                Transaction newTransaction = new Transaction();
                newTransaction.setTypeOfTransaction(this.typeOfTransaction);
                newTransaction.setDateAndTimeOfTransaction(this.dateAndTimeOfTransaction);
                newTransaction.setUser(this.user);
                newTransaction.setStock(this.stock);
                newTransaction.setQuantity(this.quantity);
                newTransaction.setPrice(this.price);
                return  newTransaction;
        }*/

}
