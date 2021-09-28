package io.redbee.socialnetwork.feeds.postLikes.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class PostLikeNotFoundException extends ResponseStatusException {
    public PostLikeNotFoundException() {
        super(NOT_FOUND, "post like not found");
    }

}
