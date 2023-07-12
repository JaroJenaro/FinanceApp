package de.iav.backend.model;


import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Document(collection = "stocks")

//                                 String id,
public record Stock(

        @MongoId
        String ISIN,
        String WKN,
        String stockTicker,
        String companyName) {

}
