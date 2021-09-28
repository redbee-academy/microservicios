package io.redbee.socialnetwork.feeds.postLikes.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class PostLikeAlreadyExistsException extends ResponseStatusException {
    public PostLikeAlreadyExistsException(Integer userId, Integer postId) {
        super(UNPROCESSABLE_ENTITY, "like for post " + postId + " by " + userId + " already exists");
    }
}
