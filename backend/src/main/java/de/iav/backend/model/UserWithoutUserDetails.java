package de.iav.backend.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserWithoutUserDetails {
    String id;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


    public String getRole() {
        return role;
    }

    String firstName;
    String lastName;
    String email;
    String role;
}
