package io.redbee.socialnetwork.shared.controller;

import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.service.UserSearchService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public abstract class SecuredController {

    private final UserSearchService searchService;

    public SecuredController(UserSearchService searchService) {
        this.searchService = searchService;
    }

    public void validateUserIsOwner(Integer ownerId) {
        Optional<User> user = searchService.getActiveBy(getUserName());

        if (!(user.isPresent() && user.get().getId().equals(ownerId))) {
            throw new ResponseStatusException(UNAUTHORIZED);
        }
    }

    public Integer getPrincipalUserId() {
        return searchService.getActiveBy(getUserName())
                .orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED))
                .getId();
    }

    private String getUserName() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
    }
}
