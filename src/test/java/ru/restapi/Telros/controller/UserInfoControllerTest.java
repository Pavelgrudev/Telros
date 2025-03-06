package ru.restapi.Telros.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.restapi.Telros.model.UserInfo;
import ru.restapi.Telros.service.UserInfoService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserInfoControllerTest {


    private MockMvc mockMvc;

    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private UserInfoController userInfoController;

    private UserInfo userInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userInfoController).build();


        userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setAddress("Москва");
        userInfo.setBio("Программист");
    }

    /**
     * Проверяет, что метод создает новую детальную информацию о пользователе.
     */
    @Test
    void testCreateUserInfo_ShouldCreateUserInfo() throws Exception {
        when(userInfoService.createUserInfo(any(UserInfo.class))).thenReturn(userInfo);

        mockMvc.perform(post("/userInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"address\": \"Москва\", \"bio\": \"Программист\"}"))
                .andExpect(status().isOk()) // Ожидаем статус 200
                .andExpect(jsonPath("$.id").value(1)) // Проверяем ID
                .andExpect(jsonPath("$.address").value("Москва")) // Проверяем адрес
                .andExpect(jsonPath("$.bio").value("Программист")); // Проверяем биографию

        // Проверяем, что метод сервиса был вызван 1 раз
        verify(userInfoService, times(1)).createUserInfo(any(UserInfo.class));
    }

    /**
     * Проверяет, что метод возвращает детальную информацию о пользователе по id
     */
    @Test
    void testGetUserInfoById_ShouldReturnUserInfo() throws Exception {

        when(userInfoService.getUserInfoById(1L)).thenReturn(Optional.of(userInfo));


        mockMvc.perform(get("/userInfo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address").value("Москва"))
                .andExpect(jsonPath("$.bio").value("Программист"));

        verify(userInfoService, times(1)).getUserInfoById(1L);
    }

    /**
     * Проверяет, что метод обновляет детальную информацию о пользователе.
     */
    @Test
    void testUpdateUserInfo_ShouldUpdateUserInfo() throws Exception {
        when(userInfoService.updateUserInfo(eq(1L), any(UserInfo.class))).thenReturn(userInfo);

        mockMvc.perform(put("/userInfo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"address\": \"Москва\", \"bio\": \"Программист\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address").value("Москва"))
                .andExpect(jsonPath("$.bio").value("Программист"));

        verify(userInfoService, times(1)).updateUserInfo(eq(1L), any(UserInfo.class));
    }

    /**
     * Проверяет, что метод удаляет детальную информацию о пользователе
     */
    @Test
    void testDeleteUserInfo_ShouldDeleteUserInfo() throws Exception {
        mockMvc.perform(delete("/userInfo/1"))
                .andExpect(status().isOk());

        verify(userInfoService, times(1)).deleteUserInfo(1L);
    }
}