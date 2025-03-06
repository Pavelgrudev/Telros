package ru.restapi.Telros.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.restapi.Telros.model.UserPhoto;
import ru.restapi.Telros.service.UserPhotoService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserPhotoControllerTest {

    @Mock
    private UserPhotoService userPhotoService;

    @InjectMocks
    private UserPhotoController userPhotoController;

    private MockMvc mockMvc;

    /**
     * Настройка мок-объектов перед выполнением тестов.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userPhotoController).build();
    }

    /**
     * Тест проверяет, что метод createUserPhoto() корректно создает фотографию пользователя.
     */
    @Test
    void createUserPhoto_ShouldReturnCreatedUserPhoto() throws Exception {
        // Arrange
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(1L);
        userPhoto.setPhotoUrl("https://example.com/photo.jpg");

        when(userPhotoService.createUserPhoto(any(UserPhoto.class))).thenReturn(userPhoto);

        // Act & Assert
        mockMvc.perform(post("/userPhotos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"photoUrl\": \"https://example.com/photo.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.photoUrl").value("https://example.com/photo.jpg"));

        verify(userPhotoService, times(1)).createUserPhoto(any(UserPhoto.class));
    }

    /**
     * Тест проверяет, что метод getUserPhotoById() корректно получает фотографию пользователя по ID.
     */
    @Test
    void getUserPhotoById_ShouldReturnUserPhoto() throws Exception {
        // Arrange
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(1L);
        userPhoto.setPhotoUrl("https://example.com/photo.jpg");

        when(userPhotoService.getUserPhotoById(1L)).thenReturn(Optional.of(userPhoto));

        // Act & Assert
        mockMvc.perform(get("/userPhotos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.photoUrl").value("https://example.com/photo.jpg"));

        verify(userPhotoService, times(1)).getUserPhotoById(1L);
    }

    /**
     * Тест проверяет, что метод updateUserPhoto() корректно обновляет фотографию пользователя.
     */
    @Test
    void updateUserPhoto_ShouldReturnUpdatedUserPhoto() throws Exception {
        // Arrange
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(1L);
        userPhoto.setPhotoUrl("https://example.com/updated-photo.jpg");

        when(userPhotoService.updateUserPhoto(anyLong(), any(UserPhoto.class))).thenReturn(userPhoto);

        // Act & Assert
        mockMvc.perform(put("/userPhotos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"photoUrl\": \"https://example.com/updated-photo.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.photoUrl").value("https://example.com/updated-photo.jpg"));

        verify(userPhotoService, times(1)).updateUserPhoto(anyLong(), any(UserPhoto.class));
    }

    /**
     * Тест проверяет, что метод deleteUserPhoto() корректно удаляет фотографию пользователя.
     */
    @Test
    void deleteUserPhoto_ShouldReturnOk() throws Exception {
        // Arrange
        doNothing().when(userPhotoService).deleteUserPhoto(1L);

        // Act & Assert
        mockMvc.perform(delete("/userPhotos/1"))
                .andExpect(status().isOk()); // Ожидаем статус 200 OK

        verify(userPhotoService, times(1)).deleteUserPhoto(1L);
    }
}