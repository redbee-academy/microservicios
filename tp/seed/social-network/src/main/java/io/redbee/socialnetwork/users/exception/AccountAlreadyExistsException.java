package io.redbee.socialnetwork.users.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class AccountAlreadyExistsException extends ResponseStatusException {
    public AccountAlreadyExistsException(String mail) {
        super(UNPROCESSABLE_ENTITY, "account " + mail + " already exists");
    }
}
