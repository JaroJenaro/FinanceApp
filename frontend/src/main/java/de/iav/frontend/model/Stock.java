package de.iav.frontend.model;

public record Stock (

        String stockTicker,
        String companyName,
        String isin,
        String wkn

){
}
