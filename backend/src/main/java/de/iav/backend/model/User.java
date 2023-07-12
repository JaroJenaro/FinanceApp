package de.iav.backend.model;


import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Document(collection = "users")
public record User(
        String id,
        String firstName,
        String lastName,
        String email,
        String password) {

    /*public User withId(User previousUser, String newId) {
        return new User(newId, previousUser.firstName, previousUser.lastName, previousUser.email, previousUser.password);

    }

     */

    public User withId(String newId) {
        return new User(newId, this.firstName, this.lastName, this.email, this.password);

    }
}
