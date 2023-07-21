package de.iav.backend.externalAPICommunication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
@RequiredArgsConstructor

public class StockDataAccess {
    private final ObjectMapper objectMapper = new ObjectMapper(); // Assuming you use Jackson
    String apiKey= "L01SUZLP38X8LFLE";

    public Double getLastPriceForStock(String stockSymbol) {
        String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+stockSymbol+"&interval=5min&outputsize=full&apikey=" + apiKey;

        try {
            // Make an HTTP request to the API and get the JSON response
            String jsonResponse = makeApiRequest(apiUrl);

            // Deserialize the JSON response into the data model
            StockResponse stockResponse = objectMapper.readValue(jsonResponse, StockResponse.class);

            // Get the TimeSeries from the StockResponse
            TimeSeries timeSeries = stockResponse.getTimeSeries();

            // Find the latest entry in the TimeSeries and get the close price
            String lastDateTime = timeSeries.getLastDateTime();
            TimeSeriesEntry lastEntry = timeSeries.getEntry(lastDateTime);

            // Return the last price as a Double
            return Double.parseDouble(lastEntry.getClose());
        } catch (IOException | InterruptedException e) {
            // Handle any exceptions that occurred during the request or deserialization
            e.printStackTrace();
            return null;
        }
    }

    private String makeApiRequest(String apiUrl) throws IOException, InterruptedException {
        // Use an HTTP client (e.g., HttpClient) to make the API request and return the response body as a String
        // You can choose your preferred way of making HTTP requests here
        // Example using HttpClient:
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
