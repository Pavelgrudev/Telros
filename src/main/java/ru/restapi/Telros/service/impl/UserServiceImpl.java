package ru.restapi.Telros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restapi.Telros.model.User;
import ru.restapi.Telros.repository.UserRepository;
import ru.restapi.Telros.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 *  Реализация сервиса для управления пользователями.
 * Предоставляет CRUD-операции с объектами User.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Получение списка всех пользователей.
     */

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Получение пользователя по id
     */

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Создание нового пользователя.
     */

    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Обновление данных пользователя.
     */
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setMiddleName(userDetails.getMiddleName());
        user.setBirthDate(userDetails.getBirthDate());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя по id
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}