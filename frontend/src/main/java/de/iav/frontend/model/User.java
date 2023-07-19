package de.iav.frontend.model;

import java.util.List;

public record User(
        String id,
        String firstName,
        String lastName,
        String email,
        String password

) {
}
