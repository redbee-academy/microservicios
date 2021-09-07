package io.redbee.socialnetwork.factory;

import io.redbee.socialnetwork.users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class UserFactory {
    public static List<User> getUsers() {
        return Arrays.asList(getUser());
    }

    public static User getUser() {
        LocalDateTime now = LocalDateTime.now();
        return new User(1,
                "test@test",
                "password",
                "OK",
                now,
                "creation_user",
                now,
                "modification_user");
    }
}
