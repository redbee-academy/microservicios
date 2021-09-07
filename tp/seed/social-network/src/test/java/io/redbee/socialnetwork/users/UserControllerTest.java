package io.redbee.socialnetwork.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.redbee.socialnetwork.factory.HeaderFactory;
import io.redbee.socialnetwork.factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("UserController Test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest  implements HeaderFactory {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDao userDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Given a request to get users, when call get user endpoint, then verify calls and return")
    void GetUsers() throws Exception {

        // Given
        List<User> users = UserFactory.getUsers();
        when(userDao.get()).thenReturn(users);

        // When
        this.mockMvc.perform(get("/api/v1/user")
                .headers(httpHeader))
                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
        // Then
        verify(userDao, only()).get();

    }
}
