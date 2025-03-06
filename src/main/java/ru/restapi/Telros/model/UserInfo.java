package ru.restapi.Telros.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс-сущность, представляющий детальную информацию о пользователе.
 */
@Entity
@Data
public class UserInfo {

    /**
     * Уникальный идентификатор UserInfo.
     * Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Адрес пользователя.
     */
    private String address;

    /**
     * Биография пользователя.
     */
    private String bio;

    /**
     * Связь "один к одному" с пользователем.
     * @JoinColumn(name = "user_id") - внешний ключ, связывающий UserInfo с User.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Конструктор для создания детальной информации о пользователе.
     */
    public UserInfo(Long id, String address, String bio, User user) {
        this.id = id;
        this.address = address;
        this.bio = bio;
        this.user = user;
    }

    /**
     * Конструктор по умолчанию.
     */
    public UserInfo() {
    }

    /**
     * Геттеры и сеттеры
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}