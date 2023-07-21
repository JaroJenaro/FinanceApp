package de.iav.backend.repository;

import de.iav.backend.model.CurrentStockPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrentStockPriceRepository extends MongoRepository<CurrentStockPrice, String> {
}
