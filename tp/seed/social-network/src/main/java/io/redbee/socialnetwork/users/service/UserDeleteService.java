package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.model.UserUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class UserDeleteService {
    private final UserModificationService modificationService;

    public UserDeleteService(UserModificationService modificationService) {
        this.modificationService = modificationService;
    }

    public User delete(Integer id) {
        return modificationService.update(
                id,
                new UserUpdateRequest(null, "DELETED")
        );
    }
}
