package io.redbee.socialnetwork.users.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException() {
        super(NOT_FOUND, "user not found");
    }
}
