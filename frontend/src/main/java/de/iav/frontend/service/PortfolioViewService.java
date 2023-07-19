package de.iav.frontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.iav.frontend.model.Transaction;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PortfolioViewService {

        private static PortfolioViewService instance;
        private final HttpClient httpClient;
        private final ObjectMapper objectMapper;
        private List<Transaction> portfolio;
        private PortfolioViewService() {
            this.httpClient = HttpClient.newHttpClient();
            this.objectMapper = new ObjectMapper();
            portfolio= new ArrayList<>();
        }

        public static synchronized PortfolioViewService getInstance() {
            if (instance == null) {
                instance = new PortfolioViewService();
            }
            return instance;
        }

        public List<Transaction> getAllTransactionsOfUser(String userId) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/api/financeapp/users/portfolio/"+userId))
                    .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(this::mapToTransactionsList) // .thenApply(responseBody -> mapToStudent(responseBody))
                    .join();
        }

        private List<Transaction> mapToTransactionsList(String responseBody) {
            try {
                return objectMapper.readValue(responseBody, new TypeReference<>() {});
                //return new ArrayList<Student>();
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to map stocksList", e);
            }

        }

    public List<Transaction> getAllTransactions() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/financeapp/transactions"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::mapToAllTransactionsList) // .thenApply(responseBody -> mapToStudent(responseBody))
                .join();
    }

    private List<Transaction> mapToAllTransactionsList(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {});
            //return new ArrayList<Student>();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map stocksList", e);
        }

    }
}

