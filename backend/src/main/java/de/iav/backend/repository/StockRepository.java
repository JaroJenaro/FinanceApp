package de.iav.backend.repository;

import de.iav.backend.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

    List<Stock> findAllByISINEqualsIgnoreCase(String isin);
}