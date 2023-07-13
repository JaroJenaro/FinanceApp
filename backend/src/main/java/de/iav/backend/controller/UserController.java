package de.iav.backend.controller;

import de.iav.backend.model.User;
import de.iav.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/financeapp/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllPets(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getPetById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public List<User> getPetsBySpecificSearch(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName ){

        if (firstName != null) {
            return userService.findAllByFirstNameEqualsIgnoreCase(firstName);
        } else {
            return userService.findAllByLastNameEqualsIgnoreCase(lastName);
        }
    }

    @GetMapping("/set")
    public List<User> setDefaultUsers() {
        return userService.setUserByRepository();
    }


    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

}
