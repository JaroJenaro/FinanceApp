package de.iav.backend.repository;

import de.iav.backend.model.CurrentStockPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentStockPriceRepository extends MongoRepository<CurrentStockPrice, String> {
}
