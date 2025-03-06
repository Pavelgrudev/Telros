package ru.restapi.Telros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.restapi.Telros.model.User;
import ru.restapi.Telros.service.UserService;
import ru.restapi.Telros.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для управления пользователями.
 * Предоставляет REST API для выполнения CRUD-операций с пользователями.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * Получить список всех пользователей
     */

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Получить пользователя по id, если найден, иначе Optional.empty()
     */

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Создать нового пользователя
     */

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    /**
     * Обновить данные пользователя.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    /**
     * Удалить пользователя по ID.
     */

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
