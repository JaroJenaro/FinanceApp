package de.iav.backend.service;

import de.iav.backend.exception.UserNotFoundException;
import de.iav.backend.model.User;

import de.iav.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }



    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }

    public List<User> findAllByFirstNameEqualsIgnoreCase(String firstName){
        return userRepository.findAllByFirstNameEqualsIgnoreCase(firstName);
    }

    public List<User> findAllByLastNameEqualsIgnoreCase(String lastName){
        return userRepository.findAllByLastNameEqualsIgnoreCase(lastName);
    }
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(String id, User userToUpdate) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        User updatedStudent = userToUpdate.withId(id);

        return userRepository.save(updatedStudent);
    }

}
