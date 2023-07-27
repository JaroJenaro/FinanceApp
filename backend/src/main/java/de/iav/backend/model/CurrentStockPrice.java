package de.iav.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "prices")
public class CurrentStockPrice {
    @Indexed(unique = true)
    String stockTicker;
    Double price;
    String dateAndTimeOfTransaction;
}
