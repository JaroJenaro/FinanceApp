package de.iav.frontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.iav.frontend.model.Stock;
import de.iav.frontend.model.Transaction;
import de.iav.frontend.model.TransactionWithoutIdDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TransactionService {
    private static TransactionService instance;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private TransactionService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public static synchronized TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }


    private Transaction mapToTransaction(String json) {
        System.out.println(json);
        try {
            return objectMapper.readValue(json, Transaction.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map transaction", e);
        }
    }

    public Transaction addTransaction(TransactionWithoutIdDTO transactionWithoutIdDTO) {
        try {
            //StudentWithoutMatriculationNumber studentDto = generateOneStudentDto();
            String requestBody = objectMapper.writeValueAsString(transactionWithoutIdDTO);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/financeapp/transactions"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(this::mapToTransaction)
                    .join();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Stock> getAllStocksByUserID(String id) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/financeapp/transactions/stocks/user/" + id))
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





}
