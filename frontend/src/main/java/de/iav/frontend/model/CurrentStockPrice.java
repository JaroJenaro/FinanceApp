package de.iav.frontend.model;

public record CurrentStockPrice (
        String stockTicker,
        Double price,
        String dateAndTimeOfTransaction
){

}
