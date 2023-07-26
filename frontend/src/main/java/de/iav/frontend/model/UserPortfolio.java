package de.iav.frontend.model;

public record UserPortfolio(
        Stock stock,
        int quantity,
        double averageBuyPrice,
        double currentPrice,
        double performance,
        double portfolioValue
){


}
