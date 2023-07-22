package de.iav.backend.controller;

import de.iav.backend.model.User;
import de.iav.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    @Test
    @DirtiesContext
    void getAllUsers() throws Exception {
        List<User> tempUsers = new ArrayList<>(Arrays.asList(
                new User("12345", "Erum", "Schuakat", "erum.schaukat@iav.de", "12345"),
                new User("23456", "Houman", "Mohammadi", "houman.mohammadi@iav.de", "23456"),
                new User("34567", "Jaroslaw", "Placzek", "jaroslaw.placzek@iav.de", "34567")
        ));
        userRepository.saveAll(tempUsers);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/financeapp/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                               [{"id":"12345","firstName":"Erum", "lastName": "Schuakat", "email" : "erum.schaukat@iav.de", "password": "12345"},{"id":"23456","firstName":"Houman", "lastName": "Mohammadi", "email" : "houman.mohammadi@iav.de", "password": "23456"},{"id":"34567","firstName":"Jaroslaw", "lastName": "Placzek", "email" : "jaroslaw.placzek@iav.de", "password": "34567"}]
                        """))
                .andExpect(jsonPath("$.length()").value(userRepository.findAll().size()));
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getTransactionByUserById() {
    }

    @Test
    void setDefaultUsers() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}