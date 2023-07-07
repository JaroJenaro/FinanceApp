package de.iav.backend.service;

import de.iav.backend.model.Stock;
import de.iav.backend.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    public final StockRepository stockRepository;


    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }


    public Optional<Stock> getStockById(String id){
        return stockRepository.findById(id);
    }

    public List<Stock> getStockByISIN(String isin){
        return stockRepository.findAllByISINEqualsIgnoreCase(isin);
    }


    /*
    public Stock createOwner(Stock student){
        return stockRepository.save(student);
    }

    public Stock updateOwner(String id, Stock petToUpdate){
        stockRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException(id));

        Stock updatedStudent = petToUpdate.withId(petToUpdate, id);

        return stockRepository.save(updatedStudent);
    }
    /*
     */





}
