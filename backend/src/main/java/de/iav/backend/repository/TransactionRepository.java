package de.iav.backend.repository;

import de.iav.backend.model.Transaction;
import de.iav.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findAllByUser(User user);

    List<Transaction> findAllByUserId(String id);

    List<Transaction> findTransactionByUser(Optional<User> user);


    //List<Transaction> findAllByUser(User user);

}