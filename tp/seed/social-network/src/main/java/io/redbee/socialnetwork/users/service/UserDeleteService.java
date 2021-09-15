package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.model.UserUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {
    private final UserUpdateService updateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeleteService.class);
    private static final UserUpdateRequest deleteUpdateRequest = new UserUpdateRequest(null, "DELETED");

    public UserDeleteService(UserUpdateService updateService) {
        this.updateService = updateService;
    }

    public User delete(Integer id) {
        User deleted = updateService.update(id, deleteUpdateRequest);

        LOGGER.info("dalete: user {} deleted", id);
        return deleted;
    }
}
