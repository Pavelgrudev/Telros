package ru.restapi.Telros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.restapi.Telros.model.UserPhoto;
import ru.restapi.Telros.service.UserPhotoService;

import java.util.Optional;

/**
 *  Контроллер для управления фотографиями пользователей.
 */

@RestController
@RequestMapping("/userPhotos")
public class UserPhotoController {

    @Autowired
    private UserPhotoService userPhotoService;

    /**
     * Загрузка новой фотографии пользователя.
     */

    @PostMapping
    public UserPhoto createUserPhoto(@RequestBody UserPhoto userPhoto) {
        return userPhotoService.createUserPhoto(userPhoto);
    }

    /**
     * Получение фотографии пользователя по id
     */

    @GetMapping("/{id}")
    public Optional<UserPhoto> getUserPhotoById(@PathVariable Long id) {
        return userPhotoService.getUserPhotoById(id);
    }

    /**
     * Обновление фотографии пользователя.
     */
    @PutMapping("/{id}")
    public UserPhoto updateUserPhoto(@PathVariable Long id, @RequestBody UserPhoto userPhoto) {
        return userPhotoService.updateUserPhoto(id, userPhoto);
    }

    /**
     * Удаление фотографии пользователя по id
     */
    @DeleteMapping("/{id}")
    public void deleteUserPhoto(@PathVariable Long id) {
        userPhotoService.deleteUserPhoto(id);
    }
}