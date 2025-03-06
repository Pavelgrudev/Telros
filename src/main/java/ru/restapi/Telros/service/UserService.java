package ru.restapi.Telros.service;

import ru.restapi.Telros.model.User;

import java.util.List;
import java.util.Optional;

/**
 *  Интерфейс для управления пользователями, содержащий методы CRUD.
 */
public interface UserService {

    /**
     * Получить список всех пользователей.
     */
    List<User> getAllUsers();

    /**
     * Получить пользователя по id
     */
    Optional<User> getUserById(Long id);

    /**
     * Создать нового пользователя.
     */
    User createUser(User user);

    /**
     * Обновить существующего пользователя.
     */
    User updateUser(Long id, User userDetails);

    /**
     * Удалить пользователя по id
     */
    void deleteUser(Long id);
}