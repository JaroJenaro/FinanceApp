package de.iav.frontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.iav.frontend.model.UserPortfolio;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PortfolioViewService {

        private static PortfolioViewService instance;
        private final HttpClient httpClient;
        private final ObjectMapper objectMapper;

        private PortfolioViewService() {
            this.httpClient = HttpClient.newHttpClient();
            this.objectMapper = new ObjectMapper();
        }

        public static synchronized PortfolioViewService getInstance() {
            if (instance == null) {
                instance = new PortfolioViewService();
            }
            return instance;
        }

    public List<UserPortfolio> getPortfolioByUserID(String id) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/financeapp/Portfolio/" + id))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::mapToPortfolioList) // .thenApply(responseBody -> mapToStudent(responseBody))
                .join();
    }

    private List<UserPortfolio> mapToPortfolioList(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to map stocks List", e);
        }
    }
}

