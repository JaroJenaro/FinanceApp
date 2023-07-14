package de.iav.backend.repository;


import de.iav.backend.model.TransactionWithoutUser;
import de.iav.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    List<User> findAllByFirstNameEqualsIgnoreCase(String firstName);

    List<User> findAllByLastNameEqualsIgnoreCase(String lastName);

    List<TransactionWithoutUser> findAllById(String id);


}
