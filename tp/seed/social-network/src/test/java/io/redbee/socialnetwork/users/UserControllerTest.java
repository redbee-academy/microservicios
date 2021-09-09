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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Given a request to get users, when call get user endpoint, then verify calls and return")
    void getUsers() throws Exception {

        // Given
        List<User> users = UserFactory.getUsers();
        when(userService.get()).thenReturn(users);

        // When
        this.mockMvc.perform(get("/api/v1/user")
                .headers(httpHeader))
                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(users)));
        // Then
        verify(userService, only()).get();

    }

    @Test
    @DisplayName("Given a user id, when call delete user endpoint, then verify calls ")
    void deleteUser() throws Exception {

        // Given
        User user = UserFactory.getUser();

        // When
        this.mockMvc.perform(delete("/api/v1/user/" + user.getId())
                .headers(httpHeader))
                //then
                .andDo(print())
                .andExpect(status().isOk());
        // Then
        verify(userService, only()).delete(user.getId());

    }

    @Test
    @DisplayName("Given a user id, when call delete user endpoint, then verify calls ")
    void getUserById() throws Exception {

        // Given
        User user = UserFactory.getUser();
        when(userService.getById(user.getId())).thenReturn(user);

        // When
        this.mockMvc.perform(get("/api/v1/user/" + user.getId())
                .headers(httpHeader))
                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
        // Then
        verify(userService, only()).getById(user.getId());

    }

}

