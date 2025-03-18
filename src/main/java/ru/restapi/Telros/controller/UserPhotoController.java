package ru.restapi.Telros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.restapi.Telros.model.UserPhoto;
import ru.restapi.Telros.service.UserPhotoService;

import java.io.IOException;
import java.util.Optional;

/**
 * Контроллер для управления фотографиями пользователей.
 */
@RestController
@RequestMapping("/user_photo")
public class UserPhotoController {

    @Autowired
    private UserPhotoService userPhotoService;

    /**
     * Загрузка новой фотографии пользователя.
     * Ожидает multipart-запрос с файлом.
     */
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<UserPhoto> createUserPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setPhotoData(file.getBytes());
        UserPhoto createdUserPhoto = userPhotoService.createUserPhoto(userPhoto);
        return new ResponseEntity<>(createdUserPhoto, HttpStatus.CREATED);
    }

    /**
     * Получение фотографии пользователя по id.
     */
    @GetMapping("/{id}")
    public Optional<UserPhoto> getUserPhotoById(@PathVariable Long id) {
        return userPhotoService.getUserPhotoById(id);
    }

    /**
     * Обновление фотографии пользователя.
     * Ожидает multipart-запрос с файлом.
     */
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")

    public ResponseEntity<UserPhoto> updateUserPhoto(
            @PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {

        UserPhoto updatedUserPhoto = userPhotoService.updateUserPhoto(id, file);
        return new ResponseEntity<>(updatedUserPhoto, HttpStatus.OK);
    }

    /**
     * Удаление фотографии пользователя по id.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserPhoto(@PathVariable Long id) {
        userPhotoService.deleteUserPhoto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

