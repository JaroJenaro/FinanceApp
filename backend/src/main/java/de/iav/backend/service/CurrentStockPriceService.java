package de.iav.backend.service;

import de.iav.backend.model.CurrentStockPrice;
import de.iav.backend.repository.CurrentStockPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CurrentStockPriceService {
    public final List<CurrentStockPrice> tempCurrentStockPrice = new ArrayList<>(Arrays.asList(
            new CurrentStockPrice("AAPL", 211.41, "2023-07-20T01:16:05.535470"),
            new CurrentStockPrice("AMZN", 131.41, "2023-07-20T01:16:05.535470"),
            new CurrentStockPrice("GOOGL", 121.41, "2023-07-20T01:16:05.535470"),
            new CurrentStockPrice("MSFT", 341.41, "2023-07-20T01:16:05.535470"),
            new CurrentStockPrice("META", 274.41, "2023-07-20T01:16:05.535470")
    ));


    public final CurrentStockPriceRepository currentStockPriceRepository;


    public List<CurrentStockPrice> getAllCurrentStockPrice() {
        return currentStockPriceRepository.findAll();
    }


    public Optional<CurrentStockPrice> getCurrentStockPriceById(String id) {
        return currentStockPriceRepository.findById(id);
    }


    public List<CurrentStockPrice> setTempCurrentStockPriceByRepository() {
        if (currentStockPriceRepository.findAll().size() == 0)
            return fillDataWithCurrentStockPrice();
        else
            return currentStockPriceRepository.findAll();

    }

    private List<CurrentStockPrice> fillDataWithCurrentStockPrice() {
        return currentStockPriceRepository.saveAll(tempCurrentStockPrice);
    }

    public CurrentStockPrice saveCurrentStockPrice(CurrentStockPrice currentStockPrice) {
        return currentStockPriceRepository.save(currentStockPrice);
    }
}
