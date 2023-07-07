package de.iav.frontend.model;

public record Stock (
        String ISIN,
        String WKN,
        String stockTicker,
        String companyName
){
}
