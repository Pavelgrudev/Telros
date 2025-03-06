package ru.restapi.Telros.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restapi.Telros.model.UserInfo;
import ru.restapi.Telros.repository.UserInfoRepository;
import ru.restapi.Telros.service.UserInfoService;

import java.util.List;
import java.util.Optional;
/**
 *  Реализация сервиса для управления детальной информацией о пользователях.
 * Предоставляет CRUD-операции с объектами UserInfo.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * Получение списка всей детальной информации о пользователях.
     */
    @Override
    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    /**
     * Получение детальной информации пользователя по id.
     */
    public Optional<UserInfo> getUserInfoById(Long id) {
        return userInfoRepository.findById(id);
    }
    /**
     * Создание новой детальной информации о пользователе.
     */
    @Override
    public UserInfo createUserInfo(UserInfo userInfo) {
        return   userInfoRepository.save(userInfo);
    }
    /**
     * Обновление детальной информации пользователя.
     */
    public UserInfo updateUserInfo(Long id, UserInfo userinfo) {
        UserInfo existingInfo = userInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Details not found"));
        existingInfo.setAddress(userinfo.getAddress());
        existingInfo.setBio(userinfo.getBio());
        return userInfoRepository.save(existingInfo);
    }

    /**
     * Удаление детальной информации пользователя по ID.
     */
    public void deleteUserInfo(Long id) {
        userInfoRepository.deleteById(id);
    }
}
