package ru.restapi.Telros.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.restapi.Telros.model.UserPhoto;
import ru.restapi.Telros.repository.UserPhotoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPhotoServiceImplTest {

    @Mock
    private UserPhotoRepository userPhotoRepository;

    @InjectMocks
    private UserPhotoServiceImpl userPhotoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Проверяет, что фотография пользователя успешно сохраняется в репозитории.
     */
    @Test
    void testCreateUserPhoto_ShouldSavePhoto() {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setPhotoData(new byte[]{1, 2, 3, 4, 5});

        when(userPhotoRepository.save(userPhoto)).thenReturn(userPhoto);

        UserPhoto savedPhoto = userPhotoService.createUserPhoto(userPhoto);

        assertNotNull(savedPhoto);
        assertArrayEquals(new byte[]{1, 2, 3, 4, 5}, savedPhoto.getPhotoData());

        verify(userPhotoRepository, times(1)).save(userPhoto);
    }

    /**
     * Проверяет, что фотография пользователя успешно возвращается по id.
     */
    @Test
    void testGetUserPhotoById_ShouldReturnPhoto() {
        Long photoId = 1L;
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(photoId);
        userPhoto.setPhotoData(new byte[]{10, 20, 30});

        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.of(userPhoto));

        Optional<UserPhoto> foundPhoto = userPhotoService.getUserPhotoById(photoId);

        assertTrue(foundPhoto.isPresent());
        assertEquals(photoId, foundPhoto.get().getId());
        assertArrayEquals(new byte[]{10, 20, 30}, foundPhoto.get().getPhotoData());

        verify(userPhotoRepository, times(1)).findById(photoId);
    }

    /**
     * Проверяет, что возвращается пустой Optional, если фотография не найдена.
     */
    @Test
    void testGetUserPhotoById_ShouldReturnEmptyOptional() {
        Long photoId = 1L;

        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.empty());

        Optional<UserPhoto> foundPhoto = userPhotoService.getUserPhotoById(photoId);

        assertFalse(foundPhoto.isPresent());

        verify(userPhotoRepository, times(1)).findById(photoId);
    }

    /**
     * Проверяет, что фотография пользователя успешно обновляется.
     */
    @Test
    void testUpdateUserPhoto_ShouldUpdatePhoto() throws Exception {
        Long photoId = 1L;

        // Старое фото в репозитории
        UserPhoto existingPhoto = new UserPhoto();
        existingPhoto.setId(photoId);
        existingPhoto.setPhotoData(new byte[]{10, 20, 30});

        // Новые данные фото
        byte[] updatedPhotoData = new byte[]{40, 50, 60};
        MockMultipartFile file = new MockMultipartFile("file", "updated_photo.jpg", MediaType.IMAGE_JPEG_VALUE, updatedPhotoData);

        UserPhoto updatedPhoto = new UserPhoto();
        updatedPhoto.setId(photoId);
        updatedPhoto.setPhotoData(updatedPhotoData);

        // Мокируем репозиторий
        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.of(existingPhoto));
        when(userPhotoRepository.save(existingPhoto)).thenReturn(updatedPhoto);

        // Теперь передаем файл в метод сервиса
        UserPhoto result = userPhotoService.updateUserPhoto(photoId, file);

        assertNotNull(result);
        assertArrayEquals(updatedPhotoData, result.getPhotoData());

        // Проверяем, что методы репозитория были вызваны
        verify(userPhotoRepository, times(1)).findById(photoId);
        verify(userPhotoRepository, times(1)).save(existingPhoto);
    }

    /**
     * Проверяет, что выбрасывается исключение, если фотография не найдена.
     */
    @Test
    void testUpdateUserPhoto_ShouldThrowException() throws Exception {
        Long photoId = 1L;

        // Имитация нового фото для загрузки
        byte[] updatedPhotoData = new byte[]{40, 50, 60};
        MockMultipartFile file = new MockMultipartFile("file", "updated_photo.jpg", MediaType.IMAGE_JPEG_VALUE, updatedPhotoData);

        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.empty());

        // Проверяем, что исключение выбрасывается, когда фото не найдено
        assertThrows(RuntimeException.class, () -> {
            userPhotoService.updateUserPhoto(photoId, file);
        });

        verify(userPhotoRepository, times(1)).findById(photoId);
        verify(userPhotoRepository, never()).save(any());
    }

    /**
     * Проверяет, что фотография пользователя успешно удаляется.
     */
    @Test
    void testDeleteUserPhoto_ShouldDeletePhoto() {
        Long photoId = 1L;

        userPhotoService.deleteUserPhoto(photoId);

        verify(userPhotoRepository, times(1)).deleteById(photoId);
    }
}
