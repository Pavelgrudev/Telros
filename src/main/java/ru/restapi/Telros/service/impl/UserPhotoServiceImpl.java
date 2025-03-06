package ru.restapi.Telros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restapi.Telros.model.UserPhoto;
import ru.restapi.Telros.repository.UserPhotoRepository;
import ru.restapi.Telros.service.UserPhotoService;

import java.util.Optional;

/**
 * Реализация сервиса для управления фотографиями пользователей.
 * Позволяет выполнять CRUD-операции с объектами UserPhoto.
 */

@Service
public class UserPhotoServiceImpl implements UserPhotoService {

    @Autowired
    private UserPhotoRepository userPhotoRepository;

    /**
     * Сохранение новой фотографии пользователя.
     */

    public UserPhoto createUserPhoto(UserPhoto userPhoto) {
        return userPhotoRepository.save(userPhoto);
    }
    /**
     * Получение фотографии пользователя по ID.
     */

    public Optional<UserPhoto> getUserPhotoById(Long id) {
        return userPhotoRepository.findById(id);
    }

    /**
     * Обновление фотографии пользователя.
     */

    public UserPhoto updateUserPhoto(Long id, UserPhoto userPhoto) {
        UserPhoto existingPhoto = userPhotoRepository.findById(id).orElseThrow(() -> new RuntimeException("Photo not found"));
        existingPhoto.setPhotoUrl(userPhoto.getPhotoUrl());
        return userPhotoRepository.save(existingPhoto);
    }

    /**
     * Удаление фотографии пользователя по ID.
     */
    public void deleteUserPhoto(Long id) {
        userPhotoRepository.deleteById(id);
    }
}