package de.iav.backend.service;

import de.iav.backend.exception.UserNotFoundException;
import de.iav.backend.model.Transaction;
import de.iav.backend.model.TransactionWithoutUser;
import de.iav.backend.model.User;
import de.iav.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    public final List<User> tempUsers = new ArrayList<>(Arrays.asList(
            /*
            new User("12345", "Erum", "Schuakat", "erum.schaukat@iav.de", "12345", new ArrayList<TransactionWithoutUser>(), "10000"),
            new User("23456", "Houman", "Mohammadi", "houman.mohammadi@iav.de", "23456", new ArrayList<TransactionWithoutUser>(), "10000"),
            new User("34567", "Jaroslaw", "Placzek", "jaroslaw.placzek@iav.de", "34567", new ArrayList<TransactionWithoutUser>(), "10000")
*/
            new User("12345", "Erum", "Schuakat", "erum.schaukat@iav.de", "12345", new ArrayList<TransactionWithoutUser>()),
            new User("23456", "Houman", "Mohammadi", "houman.mohammadi@iav.de", "23456", new ArrayList<TransactionWithoutUser>()),
            new User("34567", "Jaroslaw", "Placzek", "jaroslaw.placzek@iav.de", "34567", new ArrayList<TransactionWithoutUser>())

    ));

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(String id) {
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

    public void updateUserTransaction(Transaction transaction) {
        TransactionWithoutUser transactionWithoutUser = getTransactionWithoutUser(transaction);
        User user = transaction.user();
        userRepository.findById(user.id()).orElseThrow(() -> new UserNotFoundException(user.id()));

        User userToUpdate = user.withId(user.id());
        if (userToUpdate.portfolio() == null) {
            List<TransactionWithoutUser> arrayList = new ArrayList<>(List.of(transactionWithoutUser));
            userToUpdate = userToUpdate.withPortfolio(arrayList);
            System.out.println("if transactionWithoutUser: " + transactionWithoutUser);
        } else {

            userToUpdate.portfolio().add(transactionWithoutUser);
        }
        System.out.println("else transactionWithoutUser: " + transactionWithoutUser);
        userRepository.save(userToUpdate);
    }


    public List<User> setUserByRepository() {
        if (userRepository.findAll().size() == 0)
            return fillDataWithUsers();
        else
            return userRepository.findAll();
    }

    private TransactionWithoutUser getTransactionWithoutUser(Transaction transaction) {
        return new TransactionWithoutUser(transaction.id(), transaction.typeOfTransaction(), transaction.dateAndTimeOfTransaction(), transaction.stock(), transaction.quantity(), transaction.price());
    }

    private List<User> fillDataWithUsers() {
        return userRepository.saveAll(tempUsers);
    }

    public List<TransactionWithoutUser> getTransactionWithoutUserById(String id) {
        return userRepository.findAllById(id);
    }
}
