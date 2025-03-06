package ru.restapi.Telros.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 *  Класс-сущность, представляющий фотографию пользователя.
 */
@Data
@Entity
public class UserPhoto {

    /**
     * id фотографии генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на фотографию или закодированное изображение в формате Base64.
     */
    private String photoUrl;

    /**
     * Связь "один к одному" с пользователем.
     *
     * @JoinColumn(name = "user_id") - внешний ключ, связывающий UserPhoto с User.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Конструктор для создания объекта UserPhoto.
     */
    public UserPhoto(Long id, String photoUrl, User user) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.user = user;
    }

    /**
     * Конструктор по умолчанию.
     */
    public UserPhoto() {
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}