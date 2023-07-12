package de.iav.backend.model;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "transactions")
public record Transaction(


        String id,
        String typeOfTransaction,
        String dateAndTimeOfTransaction,
        @DBRef
        User user,
        @DBRef
        Stock stock,
        Integer quantity,
        Double price
) {
}
