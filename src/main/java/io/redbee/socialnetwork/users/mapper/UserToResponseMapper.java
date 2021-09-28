package io.redbee.socialnetwork.users.mapper;

import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.model.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserToResponseMapper {

    public UserResponse map(User user) {
        return new UserResponse(
                user.getId(),
                user.getMail(),
                user.getStatus()
        );
    }
}
