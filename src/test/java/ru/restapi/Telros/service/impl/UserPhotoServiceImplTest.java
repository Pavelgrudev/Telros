package ru.restapi.Telros.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
     * Проверяет, что фотография пользователя  успешно сохраняется в репозитории
     */
    @Test
    void testCreateUserPhoto_ShouldSavePhoto() {

        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setPhotoUrl("https://example.com/photo.jpg");


        when(userPhotoRepository.save(userPhoto)).thenReturn(userPhoto);


        UserPhoto savedPhoto = userPhotoService.createUserPhoto(userPhoto);


        assertNotNull(savedPhoto);
        assertEquals("https://example.com/photo.jpg", savedPhoto.getPhotoUrl());


        verify(userPhotoRepository, times(1)).save(userPhoto);
    }

    /**
     * Проверяет, что фотография пользователя успешно возвращается по id
     */
    @Test
    void testGetUserPhotoById_ShouldReturnPhoto() {

        Long photoId = 1L;
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(photoId);
        userPhoto.setPhotoUrl("https://example.com/photo.jpg");

        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.of(userPhoto));

        Optional<UserPhoto> foundPhoto = userPhotoService.getUserPhotoById(photoId);


        assertTrue(foundPhoto.isPresent());
        assertEquals(photoId, foundPhoto.get().getId());
        assertEquals("https://example.com/photo.jpg", foundPhoto.get().getPhotoUrl());

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
     *  Проверяет, что фотография пользователя успешно обновляется.
     */
    @Test
    void testUpdateUserPhoto_ShouldUpdatePhoto() {

        Long photoId = 1L;
        UserPhoto existingPhoto = new UserPhoto();
        existingPhoto.setId(photoId);
        existingPhoto.setPhotoUrl("https://example.com/old-photo.jpg");

        UserPhoto updatedPhoto = new UserPhoto();
        updatedPhoto.setPhotoUrl("https://example.com/new-photo.jpg");


        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.of(existingPhoto));
        when(userPhotoRepository.save(existingPhoto)).thenReturn(existingPhoto);


        UserPhoto result = userPhotoService.updateUserPhoto(photoId, updatedPhoto);


        assertNotNull(result);
        assertEquals("https://example.com/new-photo.jpg", result.getPhotoUrl());

        verify(userPhotoRepository, times(1)).findById(photoId);
        verify(userPhotoRepository, times(1)).save(existingPhoto);
    }

    /**
     * Проверяет, что выбрасывается исключение, если фотография не найдена
     */
    @Test
    void testUpdateUserPhoto_ShouldThrowException() {

        Long photoId = 1L;
        UserPhoto updatedPhoto = new UserPhoto();
        updatedPhoto.setPhotoUrl("https://example.com/new-photo.jpg");

        when(userPhotoRepository.findById(photoId)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class, () -> {
            userPhotoService.updateUserPhoto(photoId, updatedPhoto);
        });


        verify(userPhotoRepository, times(1)).findById(photoId);
        verify(userPhotoRepository, never()).save(any());
    }

    /**
     * Проверяет, что фотография пользователя успешно удаляется
     */
    @Test
    void testDeleteUserPhoto_ShouldDeletePhoto() {

        Long photoId = 1L;

        userPhotoService.deleteUserPhoto(photoId);

        verify(userPhotoRepository, times(1)).deleteById(photoId);
    }
}