package de.iav.frontend.model;

public record Stock(

        String stockTicker,
        String companyName,
        String isin,
        String wkn

) {
    public String toString() {
        return stockTicker + ": " + companyName + ": " + isin;
    }
}
