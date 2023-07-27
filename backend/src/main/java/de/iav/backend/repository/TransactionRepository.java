package de.iav.backend.repository;

import de.iav.backend.model.Transaction;
import de.iav.backend.model.UserWithoutUserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findAllByUserId(String id);

    List<Transaction> findTransactionByUser(UserWithoutUserDetails user);


}