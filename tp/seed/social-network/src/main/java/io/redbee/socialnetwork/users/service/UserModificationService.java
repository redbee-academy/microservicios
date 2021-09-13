package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.users.builder.UserBuilder;
import io.redbee.socialnetwork.users.dao.UserDao;
import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.model.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserModificationService {

    private final UserSearchService searchService;
    private final UserDao dao;

    public UserModificationService(UserSearchService searchService, UserDao dao) {
        this.searchService = searchService;
        this.dao = dao;
    }

    public User update(Integer id, UserUpdateRequest updateRequest) {
        User updated = new UserBuilder()
                .basedOn(searchService.get(id))
                .status(updateRequest.getStatus())
                .modificationDate(LocalDateTime.now())
                .modificationUser("user")
                .build();

        dao.update(updated);
        return updated;
    }
}
