package ru.restapi.Telros.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.restapi.Telros.model.UserInfo;
import ru.restapi.Telros.repository.UserInfoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInfoServiceImplTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @InjectMocks
    private UserInfoServiceImpl userInfoService;

    /**
     * Инициализация мок-объектов перед каждым тестом
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     *Тест проверяет, что метод getAllUserInfo() возвращает список объектов UserInfo.
     */
    @Test
    void getAllUserInfo_ShouldReturnListOfUserInfo() {
        // Arrange
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(1L);
        userInfo1.setAddress("Address 1");
        userInfo1.setBio("Bio 1");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setId(2L);
        userInfo2.setAddress("Address 2");
        userInfo2.setBio("Bio 2");

        List<UserInfo> userInfoList = Arrays.asList(userInfo1, userInfo2);

        when(userInfoRepository.findAll()).thenReturn(userInfoList);

        // Act
        List<UserInfo> result = userInfoService.getAllUserInfo();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Address 1", result.get(0).getAddress());
        assertEquals("Bio 2", result.get(1).getBio());

        verify(userInfoRepository, times(1)).findAll();
    }

    /**
     * Тест проверяет, что метод getUserInfoById() возвращает правильный объект UserInfo.
     */
    @Test
    void getUserInfoById_ShouldReturnUserInfo() {
        // Arrange
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setAddress("Address 1");
        userInfo.setBio("Bio 1");

        when(userInfoRepository.findById(1L)).thenReturn(Optional.of(userInfo));

        // Act
        Optional<UserInfo> result = userInfoService.getUserInfoById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Address 1", result.get().getAddress());
        assertEquals("Bio 1", result.get().getBio());

        verify(userInfoRepository, times(1)).findById(1L);
    }

    /**
     * Тест проверяет, что метод createUserInfo() корректно создает объект UserInfo.
     */
    @Test
    void createUserInfo_ShouldReturnCreatedUserInfo() {
        // Arrange
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setAddress("Address 1");
        userInfo.setBio("Bio 1");

        when(userInfoRepository.save(userInfo)).thenReturn(userInfo);

        // Act
        UserInfo result = userInfoService.createUserInfo(userInfo);

        // Assert
        assertNotNull(result);
        assertEquals("Address 1", result.getAddress());
        assertEquals("Bio 1", result.getBio());

        verify(userInfoRepository, times(1)).save(userInfo);
    }

    /**
     * Тест проверяет, что метод updateUserInfo() корректно обновляет объект UserInfo.
     */
    @Test
    void updateUserInfo_ShouldReturnUpdatedUserInfo() {
        // Arrange
        UserInfo existingUserInfo = new UserInfo();
        existingUserInfo.setId(1L);
        existingUserInfo.setAddress("Old Address");
        existingUserInfo.setBio("Old Bio");

        UserInfo updatedUserInfo = new UserInfo();
        updatedUserInfo.setAddress("New Address");
        updatedUserInfo.setBio("New Bio");

        when(userInfoRepository.findById(1L)).thenReturn(Optional.of(existingUserInfo));
        when(userInfoRepository.save(existingUserInfo)).thenReturn(existingUserInfo);

        // Act
        UserInfo result = userInfoService.updateUserInfo(1L, updatedUserInfo);

        // Assert
        assertNotNull(result);
        assertEquals("New Address", result.getAddress());
        assertEquals("New Bio", result.getBio());

        verify(userInfoRepository, times(1)).findById(1L);
        verify(userInfoRepository, times(1)).save(existingUserInfo);
    }

    /**
     * Тест проверяет, что метод deleteUserInfo() корректно удаляет объект UserInfo по id
     */
    @Test
    void deleteUserInfo_ShouldDeleteUserInfo() {
        // Arrange
        doNothing().when(userInfoRepository).deleteById(1L);

        // Act
        userInfoService.deleteUserInfo(1L);

        // Assert
        verify(userInfoRepository, times(1)).deleteById(1L);
    }
}