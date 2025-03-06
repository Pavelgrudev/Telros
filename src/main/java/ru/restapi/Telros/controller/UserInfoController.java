package ru.restapi.Telros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.restapi.Telros.model.UserInfo;
import ru.restapi.Telros.service.UserInfoService;

import java.util.Optional;

/**
 *  Контроллер для управления детальной информацией
 * Предоставляет REST API для выполнения CRUD-операций с объектами UserInfo
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;

    /**
     * Создание новой детальной информации о пользователе.
     */

    @PostMapping
    public UserInfo createUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.createUserInfo(userInfo);
    }

    /**
     * Получение детальной информации о пользователе по его ID.
     */

    @GetMapping("/{id}")
    public Optional<UserInfo> getUserInfoById(@PathVariable Long id) {
        return userInfoService.getUserInfoById(id);
    }

    /**
     * Обновление детальной информации пользователя.
     */

    @PutMapping("/{id}")
    public UserInfo updateUserInfo(@PathVariable Long id, @RequestBody UserInfo userDetails) {
        return userInfoService.updateUserInfo(id, userDetails);
    }
    /**
     * Удаление детальной информации о пользователе по id.
     */

    @DeleteMapping("/{id}")
    public void deleteUserInfo(@PathVariable Long id) {
        userInfoService.deleteUserInfo(id);
    }
}