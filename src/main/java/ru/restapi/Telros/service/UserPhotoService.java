package ru.restapi.Telros.service;

import ru.restapi.Telros.model.UserPhoto;

import java.util.Optional;

/**
 *  Интерфейс для управления фотографиями пользователей.
 */
public interface UserPhotoService {

    /**
     * Создать новую фотографию пользователя.
     */
    UserPhoto createUserPhoto(UserPhoto userPhoto);

    /**
     * Получить фотографию пользователя по ID.
     */
    Optional<UserPhoto> getUserPhotoById(Long id);

    /**
     * Обновить существующую фотографию пользователя.
     */
    UserPhoto updateUserPhoto(Long id, UserPhoto userPhoto);

    /**
     * Удалить фотографию пользователя по id
     */
    void deleteUserPhoto(Long id);
}