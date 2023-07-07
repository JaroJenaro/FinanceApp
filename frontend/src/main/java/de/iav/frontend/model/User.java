package de.iav.frontend.model;

public record User(
        String userId,
        String firstName,
        String lastName,
        String email,
        String password

) {
}
