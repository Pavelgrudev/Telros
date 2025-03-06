package ru.restapi.Telros.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.restapi.Telros.model.User;
import ru.restapi.Telros.service.UserService;

import java.time.LocalDate;
import java.util.Optional;


@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {


    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser_ShouldSaveUserToDatabase() {

        /**
         *  Создаем нового пользователя
         *  */

        User user = new User();
        user.setLastName("Иванов");
        user.setFirstName("Иван");
        user.setMiddleName("Иванович");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setEmail("ivanov@example.com");
        user.setPhoneNumber("+79991234567");

       /**
       * Сохраняем пользователя в базу данных
       */

        User savedUser = userService.createUser(user);


        /**
         * Проверяем, что пользователь успешно сохранен и имеет ID
        */

        assertNotNull(savedUser.getId());
        assertEquals("Иванов", savedUser.getLastName());
    }

    @Test
    public void testGetUserById_ShouldReturnUserFromDatabase() {

        /**
        * Создаем нового пользователя
        */

        User user = new User();
        user.setLastName("Иванов");
        user.setFirstName("Иван");
        user.setEmail("ivanov@example.com");

        /**
        * Сохраняем пользователя в базу данных
        */

        userService.createUser(user);

        /**
        * Получаем пользователя по id
        */

        Optional<User> foundUser = userService.getUserById(user.getId());

        /**
        * Проверяем, что пользователь найден и его данные корректны
        */

        assertTrue(foundUser.isPresent());
        assertEquals("Иванов", foundUser.get().getLastName());
    }

    @Test
    public void testUpdateUser_ShouldUpdateUserInDatabase() {

        /**
         * Создаем нового пользователя
         * */

        User user = new User();
        user.setLastName("Иванов");
        user.setFirstName("Иван");
        userService.createUser(user);

        /**
         * Обновляем данные пользователя
        */

        user.setLastName("Петров");
        User updatedUser = userService.updateUser(user.getId(), user);

        /**
         * Проверяем, что данные пользователя успешно обновлены
         * */

        assertEquals("Петров", updatedUser.getLastName());
    }

    @Test
    public void testDeleteUser_ShouldRemoveUserFromDatabase() {

       /**
        * Создаем нового пользователя
        */

        User user = new User();
        user.setLastName("Иванов");
        user.setFirstName("Иван");
        userService.createUser(user);

        /**
         * Удаляем пользователя по ID
         */

        userService.deleteUser(user.getId());

        /**
         * Пытаемся найти удаленного пользователя
         */

        Optional<User> deletedUser = userService.getUserById(user.getId());

        /**
         * Проверяем, что пользователь больше не существует в базе данных
         */

        assertFalse(deletedUser.isPresent());
    }
}