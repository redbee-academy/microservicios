package io.redbee.socialnetwork.users;

import io.redbee.socialnetwork.factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("given mock use DAO when call delete user then verify call")
    void obtenerEstados() {
        // Given
        User user = UserFactory.getUser();
        when(userDao.getById(user.getId())).thenReturn(Optional.of(user));

        // When
        userService.delete(user.getId());

        // Then
        verify(userDao, times(1)).getById(user.getId());
        verify(userDao, times(1)).update(eq(user));


    }


}
