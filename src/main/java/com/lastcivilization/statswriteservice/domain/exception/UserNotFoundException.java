package com.lastcivilization.statswriteservice.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String keycloakId) {
        super("Can not found user with id: $s".formatted(keycloakId));
    }
}
