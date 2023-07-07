package de.iav.backend.model;

public record Stock(String ISIN,
                    String WKN,
                    String stockTicker,
                    String companyName) {

}
