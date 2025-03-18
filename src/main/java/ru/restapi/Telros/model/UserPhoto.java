package ru.restapi.Telros.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс-сущность, представляющий фотографию пользователя.
 */
@Data
@Entity
@Table(name = "user_photo")
public class UserPhoto {

    /**
     * id фотографии генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Данные фотографии в бинарном формате.
     */
    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] photoData;

    /**
     * Связь "один к одному" с пользователем.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Конструктор для создания объекта UserPhoto.
     */
    public UserPhoto(Long id, byte[] photoData, User user) {
        this.id = id;
        this.photoData = photoData;
        this.user = user;
    }

    /**
     * Конструктор по умолчанию.
     */
    public UserPhoto() {
    }

    /**
     * Геттеры и сеттеры.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}