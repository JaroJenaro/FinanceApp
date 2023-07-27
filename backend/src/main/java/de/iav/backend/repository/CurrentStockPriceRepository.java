package de.iav.backend.repository;

import de.iav.backend.model.CurrentStockPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentStockPriceRepository extends MongoRepository<CurrentStockPrice, String> {


    Optional<CurrentStockPrice> findByStockTicker(String stockTicker);
}
