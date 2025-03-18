//package ru.restapi.Telros.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.multipart.MultipartFile;
//import ru.restapi.Telros.model.UserPhoto;
//import ru.restapi.Telros.service.UserPhotoService;
//
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class UserPhotoControllerTest {
//
//    @Mock
//    private UserPhotoService userPhotoService;
//
//    @InjectMocks
//    private UserPhotoController userPhotoController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(userPhotoController).build();
//    }
//
//    /**
//     * Тест проверяет, что метод createUserPhoto() корректно создает фотографию пользователя.
//     */
//    @Test
//    void createUserPhoto_ShouldReturnCreatedUserPhoto() throws Exception {
//        // Подготовка данных для теста
//        byte[] photoData = "test image data".getBytes();
//        MockMultipartFile file = new MockMultipartFile("file", "photo.jpg", MediaType.IMAGE_JPEG_VALUE, photoData);
//
//        UserPhoto userPhoto = new UserPhoto();
//        userPhoto.setId(1L);
//        userPhoto.setPhotoData(photoData);
//
//        // Мокаем вызов сервиса, который должен вернуть созданное фото
//        when(userPhotoService.createUserPhoto(any(UserPhoto.class))).thenReturn(userPhoto);
//
//        // Выполнение теста с помощью MockMvc
//        mockMvc.perform(multipart("/userPhotos")
//                        .file(file) // Передаем файл в виде multipart
//                        .contentType(MediaType.MULTIPART_FORM_DATA)) // Указываем тип контента multipart
//                .andExpect(status().isCreated())  // Ожидаем статус 201 CREATED
//                .andExpect(jsonPath("$.id").value(1));  // Проверяем ID созданного фото
//
//        // Проверяем, что метод сервиса был вызван один раз с объектом UserPhoto
//        verify(userPhotoService, times(1)).createUserPhoto(any(UserPhoto.class));
//    }
//    /**
//     * Тест проверяет, что метод getUserPhotoById() корректно получает фотографию пользователя по ID.
//     */
//    @Test
//    void getUserPhotoById_ShouldReturnUserPhoto() throws Exception {
//        byte[] photoData = "test image data".getBytes();
//
//        UserPhoto userPhoto = new UserPhoto();
//        userPhoto.setId(1L);
//        userPhoto.setPhotoData(photoData);
//
//        when(userPhotoService.getUserPhotoById(1L)).thenReturn(Optional.of(userPhoto));
//
//        mockMvc.perform(get("/userPhotos/1"))
//                .andExpect(status().isOk());
//
//        verify(userPhotoService, times(1)).getUserPhotoById(1L);
//    }
//
//    /**
//     * Тест проверяет, что метод updateUserPhoto() корректно обновляет фотографию пользователя.
//     */
//    @Test
//    void updateUserPhoto_ShouldReturnUpdatedUserPhoto() throws Exception {
//        byte[] updatedPhotoData = "updated image data".getBytes();
//        MockMultipartFile file = new MockMultipartFile("file", "updated_photo.jpg", MediaType.IMAGE_JPEG_VALUE, updatedPhotoData);
//
//        UserPhoto updatedUserPhoto = new UserPhoto();
//        updatedUserPhoto.setId(1L);
//        updatedUserPhoto.setPhotoData(updatedPhotoData);
//
//        // Мокаем вызов updateUserPhoto
//        when(userPhotoService.updateUserPhoto(eq(1L), any(MultipartFile.class))).thenReturn(updatedUserPhoto);
//
//        // Выполняем запрос с загрузкой файла
//        mockMvc.perform(multipart("/userPhotos/1")
//                        .file(file)
//                        .with(request -> {
//                            request.setMethod("PUT");
//                            return request;
//                        }))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L));
//
//        // Проверяем, что сервис был вызван один раз
//        verify(userPhotoService, times(1)).updateUserPhoto(eq(1L), any(MultipartFile.class));
//    }
//
//    /**
//     * Тест проверяет, что метод deleteUserPhoto() корректно удаляет фотографию пользователя.
//     */
//    @Test
//    void deleteUserPhoto_ShouldReturnOk() throws Exception {
//        doNothing().when(userPhotoService).deleteUserPhoto(1L);
//
//        // Выполнение запроса на удаление фото
//        mockMvc.perform(delete("/userPhotos/1"))
//                .andExpect(status().isNoContent());  // Ожидаем статус 204 No Content
//
//        // Проверяем, что метод deleteUserPhoto был вызван один раз
//        verify(userPhotoService, times(1)).deleteUserPhoto(1L);
//    }
//}
package ru.restapi.Telros.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.restapi.Telros.model.UserPhoto;
import ru.restapi.Telros.service.UserPhotoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserPhotoControllerTest {

    @Mock
    private UserPhotoService userPhotoService;

    @InjectMocks
    private UserPhotoController userPhotoController;

    private MockMvc mockMvc;

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
        byte[] photoData = "test image data".getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "photo.jpg", MediaType.IMAGE_JPEG_VALUE, photoData);

        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(1L);
        userPhoto.setPhotoData(photoData);

        when(userPhotoService.createUserPhoto(any(UserPhoto.class))).thenReturn(userPhoto);

        mockMvc.perform(multipart("/user_photo")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(userPhotoService, times(1)).createUserPhoto(any(UserPhoto.class));
    }

    /**
     * Тест проверяет, что метод getUserPhotoById() корректно возвращает фотографию по ID.
     */
    @Test
    public void whenGetUserPhotoById_thenReturnUserPhoto() {
        // Arrange
        Long id = 1L;
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(id);
        userPhoto.setPhotoData(new byte[]{1, 2, 3});

        given(userPhotoService.getUserPhotoById(id)).willReturn(Optional.of(userPhoto));

        // Act
        Optional<UserPhoto> result = userPhotoController.getUserPhotoById(id);

        // Assert
        assertEquals(userPhoto, result.get());
    }

    @Test
    public void whenGetUserPhotoById_thenReturnEmpty() {
        // Arrange
        Long id = 2L;
        given(userPhotoService.getUserPhotoById(id)).willReturn(Optional.empty());

        // Act
        Optional<UserPhoto> result = userPhotoController.getUserPhotoById(id);

        // Assert
        assertEquals(Optional.empty(), result);
    }
    /**
     * Тест проверяет, что метод updateUserPhoto() корректно обновляет фотографию пользователя.
     */
    @Test
    void updateUserPhoto_ShouldReturnUpdatedUserPhoto() throws Exception {
        byte[] updatedPhotoData = "updated image data".getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "updated_photo.jpg", MediaType.IMAGE_JPEG_VALUE, updatedPhotoData);

        UserPhoto updatedUserPhoto = new UserPhoto();
        updatedUserPhoto.setId(1L);
        updatedUserPhoto.setPhotoData(updatedPhotoData);

        when(userPhotoService.updateUserPhoto(eq(1L), any(MultipartFile.class))).thenReturn(updatedUserPhoto);

        mockMvc.perform(multipart("/user_photo/1")
                        .file(file)
                        .with(request -> {
                            request.setMethod("PUT");
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(userPhotoService, times(1)).updateUserPhoto(eq(1L), any(MultipartFile.class));
    }
    /**
     * Тест проверяет, что метод deleteUserPhoto() корректно удаляет фотографию пользователя.
     */
    @Test
    void deleteUserPhoto_ShouldReturnNoContent() throws Exception {
        doNothing().when(userPhotoService).deleteUserPhoto(1L);

        mockMvc.perform(delete("/user_photo/1"))
                .andExpect(status().isNoContent());

        verify(userPhotoService, times(1)).deleteUserPhoto(1L);
    }
}