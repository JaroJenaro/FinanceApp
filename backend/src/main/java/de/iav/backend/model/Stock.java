package de.iav.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "stocks")
public class Stock{


        @MongoId
        String ISIN;
        String WKN;
        String stockTicker;
        String companyName;

}
