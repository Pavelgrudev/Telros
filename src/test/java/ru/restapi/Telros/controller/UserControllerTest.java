package ru.restapi.Telros.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.restapi.Telros.model.User;
import ru.restapi.Telros.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();


        user1 = new User();
        user1.setId(1L);
        user1.setFirstName("Иван");

        user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Петр");
    }

    /**
     * Проверяет, что Метод возвращает список всех пользователей
     */
    @Test
    void testGetAllUsers_ShouldReturnListOfUsers() throws Exception {

        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Иван"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Петр"));

        verify(userService, times(1)).getAllUsers();
    }

    /**
     * Проверяет , что Метод возвращает пользователя по id
     */
    @Test
    void testGetUserById_ShouldReturnUser() throws Exception {

        when(userService.getUserById(2L)).thenReturn(Optional.of(user2));

        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.firstName").value("Петр"));

        verify(userService, times(1)).getUserById(2L);
    }

    /**
     * Проверяет , что Метод создает нового пользователя.
     */
    @Test
    void testCreateUser_ShouldCreateUser() throws Exception {

        when(userService.createUser(any(User.class))).thenReturn(user1);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"firstName\": \"Иван\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Иван"));

        verify(userService, times(1)).createUser(any(User.class));
    }

    /**
     * Проверяет ,что Метод обновляет пользователя.
     */
    @Test
    void testUpdateUser_ShouldUpdateUser() throws Exception {

        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(user1);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"firstName\": \"Иван\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Иван"));

        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    /**
     *Проверяет ,что  Метод удаляет пользователя.
     */
    @Test
    void testDeleteUser_ShouldDeleteUser() throws Exception {

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }
}