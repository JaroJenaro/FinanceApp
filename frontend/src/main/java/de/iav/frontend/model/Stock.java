package de.iav.frontend.model;

import java.math.BigDecimal;

public record Stock (

        String ISIN,
        String WKN,
        String stockTicker,
        String companyName

){
}
