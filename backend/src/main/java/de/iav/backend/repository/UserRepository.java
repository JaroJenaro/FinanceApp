package de.iav.backend.repository;


import de.iav.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    List<User> findAllById(String id);


}