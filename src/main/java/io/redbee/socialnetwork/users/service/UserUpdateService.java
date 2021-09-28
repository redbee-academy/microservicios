package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.users.dao.UserDao;
import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.model.UserUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    private final UserSearchService searchService;
    private final UserDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdateService.class);

    public UserUpdateService(UserSearchService searchService, UserDao dao) {
        this.searchService = searchService;
        this.dao = dao;
    }

    public User update(Integer id, UserUpdateRequest updateRequest) {
        User updated = updateUserFields(
                searchService.getBy(id),
                updateRequest
        );

        dao.update(updated);

        LOGGER.info("update: updated user {}", updated);
        return updated;
    }

    private User updateUserFields(User user, UserUpdateRequest updateRequest) {
        User userWithUpdatedFields = user;

        if (updateRequest.getMail() != null) {
            userWithUpdatedFields = userWithUpdatedFields.copyMail(updateRequest.getMail());
        }

        if (updateRequest.getStatus() != null) {
            userWithUpdatedFields = userWithUpdatedFields.copyStatus(updateRequest.getStatus());
        }

        if (userWithUpdatedFields != user) {
            userWithUpdatedFields = userWithUpdatedFields.copyModificationAudit();
        }

        return userWithUpdatedFields;
    }
}
