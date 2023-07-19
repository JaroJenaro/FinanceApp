package de.iav.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
public class User{
    @MongoId
    String id;
    String firstName;
    String lastName;
    String email;
    String password;


        public User withId(String newId) {
            //return new User(newId, this.firstName, this.lastName, this.email, this.password, this.portfolio, this.moneyAccount);
            return new User(newId, this.firstName, this.lastName, this.email, this.password);

        }
}