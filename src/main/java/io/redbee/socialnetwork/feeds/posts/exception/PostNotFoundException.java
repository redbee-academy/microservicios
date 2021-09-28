package io.redbee.socialnetwork.feeds.posts.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class PostNotFoundException extends ResponseStatusException {
    public PostNotFoundException() {
        super(NOT_FOUND, "post not found");
    }
}
