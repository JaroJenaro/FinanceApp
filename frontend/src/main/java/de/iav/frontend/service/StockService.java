package de.iav.frontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.iav.frontend.model.CurrentStockPrice;
import de.iav.frontend.model.Stock;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class StockService {
    private static StockService instance;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public StockService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public static synchronized StockService getInstance() {
        if (instance == null) {
            instance = new StockService();
        }
        return instance;
    }

    public List<Stock> getAllStocks() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/financeapp/stocks"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::mapToStockList) // .thenApply(responseBody -> mapToStudent(responseBody))
                .join();
    }

    private List<Stock> mapToStockList(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map stocks List", e);
        }

    }

    public CurrentStockPrice getStockPrice(String stockSymbol) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/financeapp/prices/" + stockSymbol))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::mapToDCurrentStockPrice) // .thenApply(responseBody -> mapToStudent(responseBody))
                .join();
    }

    private CurrentStockPrice mapToDCurrentStockPrice(String json) {
        System.out.println(" private Double mapToDouble(String json) {" + json + "}");
        try {
            return objectMapper.readValue(json, CurrentStockPrice.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map Double", e);
        }
    }

}
