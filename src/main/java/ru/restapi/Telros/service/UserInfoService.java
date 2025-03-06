package ru.restapi.Telros.service;

import ru.restapi.Telros.model.UserInfo;

import java.util.List;
import java.util.Optional;


public interface UserInfoService {


    /**
     * Список всех записей UserInfo
     */
    List<UserInfo> getAllUserInfo();

    /**
     * Optional, содержащий найденную запись, или пустую запись.
     */
    Optional<UserInfo> getUserInfoById(Long id);

    /**
     * @return сохраненный объект UserInfo
     */
    UserInfo createUserInfo(UserInfo userInfo);

    /**
     * id  запись, которую нужно обновить
     */
    UserInfo updateUserInfo(Long id, UserInfo userInfo);

    /**
     *  id записи, которую нужно удалить
     */
    void deleteUserInfo(Long id);
}