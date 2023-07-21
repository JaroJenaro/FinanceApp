package de.iav.backend.service;

import de.iav.backend.exception.UserNotFoundException;
import de.iav.backend.model.User;
import de.iav.backend.repository.StockRepository;
import de.iav.backend.repository.TransactionRepository;
import de.iav.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    TransactionRepository transactionRepository = mock(TransactionRepository.class);
    StockRepository stockRepository = mock(StockRepository.class);

    UserService userService = new UserService(userRepository, transactionRepository, stockRepository);

    @Test
    void getUserById_whenExistingId_thenReturnUser() {
        //GIVEN
        String expectedUserId = "12345";
        User expectedUser = new User(expectedUserId, "Erum", "Schuakat", "erum.schaukat@iav.de", "12345");

        // Wie soll sich das "gemockte" Repository verhalten?
        // Der Dummy soll das expectedProduct liefern, wenn ein Aufruf von getProductById
        // mit der ID expectedProductId kommt
        when(userRepository.findById(expectedUserId)).thenReturn(Optional.of(expectedUser));


        //WHEN
        Optional<User> actualUser = userService.getUserById(expectedUserId);

        //THEN
        assertTrue(actualUser.isPresent());
        assertEquals(expectedUser, actualUser.get());
        // Sicherstellen, dass getProductById auch WIRKLICH aufgerufen wurde
        verify(userRepository).findById(any());
    }

    @Test
    void updateUserById_whenNonExistingId_thenThrowUserNotFoundException() {
        User user = new User("userId",  "Erum", "Schuakat", "erum.schaukat@iav.de", "12345");
        //GIVEN
        when(userRepository.findById("nonExistingId")).thenThrow(UserNotFoundException.class);

        //WHEN + THEN
        assertThrows(UserNotFoundException.class, () -> userService.updateUser("nonExistingId",user));
    }

}