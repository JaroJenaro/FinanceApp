package de.iav.backend.repository;

import de.iav.backend.model.Stock;
import de.iav.backend.model.Transaction;
import de.iav.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Stock> findAllByUser(User user);

    //List<Transaction> findAllByUser(User user);

}
