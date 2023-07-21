package de.iav.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "prices")
public class CurrentStockPrice {
    @MongoId
    @Indexed(unique = true)
    String stockTicker;
    Double price;
    String dateAndTimeOfTransaction;
}
