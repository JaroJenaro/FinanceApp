package de.iav.frontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.iav.frontend.model.TransactionWithoutUser;

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
        private List<TransactionWithoutUser> portfolio;
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

        public List<TransactionWithoutUser> getAllTransactionsOfUser(String userId) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/api/financeapp/user/"+userId+"portfolio"))
                    .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(this::mapToTransactionsList) // .thenApply(responseBody -> mapToStudent(responseBody))
                    .join();
        }

        private List<TransactionWithoutUser> mapToTransactionsList(String responseBody) {
            try {
                return objectMapper.readValue(responseBody, new TypeReference<>() {});
                //return new ArrayList<Student>();
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to map stocksList", e);
            }

        }

    public Double getPortfolioValue(String userId) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/financeapp/user/"+userId+"portfoliovalue"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::mapToPortfolio) // .thenApply(responseBody -> mapToStudent(responseBody))
                .join();
    }

    private Double mapToPortfolio(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {});

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map stocksList", e);
        }

    }
}

