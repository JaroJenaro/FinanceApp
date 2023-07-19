package de.iav.backend.model;


import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;


@Builder
@Document(collection = "users")
public record User(
        @MongoId
        String id,
        String firstName,
        String lastName,
        String email,
        String password) {


    public User withId(String newId) {
        //return new User(newId, this.firstName, this.lastName, this.email, this.password, this.portfolio, this.moneyAccount);
        return new User(newId, this.firstName, this.lastName, this.email, this.password);

    }

    public User withPortfolio(List<Transaction> portfolio) {

        return new User(this.id, this.firstName, this.lastName, this.email, this.password);

    }

}