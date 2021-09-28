package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.users.dao.UserDao;
import io.redbee.socialnetwork.users.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserSearchServiceTests {

    UserDao dao = Mockito.mock(UserDao.class);
    UserSearchService service = new UserSearchService(dao);

    @Test
    void get_by_valid_id() throws Exception {

        //given
        Integer id = 123;

        //and
        Mockito.when(dao.getById(id)).thenReturn(Optional.of(aUser()));

        //when
        User result = service.getBy(id);

        //then
        assertEquals(aUser(), result);

    }

    private User aUser() {
        return new User(
                1,
                "mail",
                "password",
                "status",
                LocalDateTime.MAX,
                "creation user",
                LocalDateTime.MAX,
                "mod user"
        );
    }
}
