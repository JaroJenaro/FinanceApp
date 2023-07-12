package de.iav.backend.service;

import de.iav.backend.model.Stock;
import de.iav.backend.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    public final List<Stock> tempStocks = new ArrayList<>(Arrays.asList(
            new Stock("US0378331005","865985", "AAPL", "Apple Inc"),
            new Stock("US0231351067","906866", "AMZN", "Amazon.com, Inc."),
            new Stock("US02079K3059","A14Y6F", "GOOGL", "Alphabet Inc. (Google)"),
            new Stock("US5949181045","870747", "MSFT", "Microsoft Corporation"),
            new Stock("US30303M1027","A1JWVX", "FB", "Facebook, Inc.")
    ));


    public final StockRepository stockRepository;


    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }


    public Optional<Stock> getStockById(String id){
        return stockRepository.findById(id);
    }


    public List<Stock> setStockByRepository(){
        if (stockRepository.findAll().size() == 0)
            return fillDataWithStocks();
        else
            return stockRepository.findAll();

    }

    private List<Stock> fillDataWithStocks(){
        return stockRepository.saveAll(tempStocks);
    }

}