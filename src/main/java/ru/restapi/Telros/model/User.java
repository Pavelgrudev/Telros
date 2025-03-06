package ru.restapi.Telros.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 *  Класс-сущность, представляющий пользователя системы.
 * Хранит основную информацию о пользователе.
 */
@Entity
@Data
@Table(name = "users")
public class User {

    /**
     * Уникальный идентификатор пользователя.
     * Генерируется автоматически.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Фамилия пользователя.
     */
    private String lastName;

    /**
     * Имя пользователя.
     */
    private String firstName;

    /**
     * Отчество пользователя.
     */
    private String middleName;

    /**
     * Дата рождения пользователя.
     */
    private LocalDate birthDate;

    /**
     * Электронная почта пользователя.
     */
    private String email;

    /**
     * Номер телефона пользователя.
     */
    private String phoneNumber;

    /**
     * Связь "один к одному" с детальной информацией о пользователе.
     * mappedBy = "user" означает, что связь управляется со стороны `UserInfo`.
     * CascadeType.ALL — каскадное выполнение операций (например, удаление).
     * orphanRemoval = true — удаление `UserInfo`, если оно отсоединяется от `User`.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserInfo userInfo;

    /**
     * Конструктор для создания пользователя с параметрами.
     *
     * @param id          идентификатор пользователя
     * @param lastName    фамилия
     * @param firstName   имя
     * @param middleName  отчество
     * @param birthDate   дата рождения
     * @param email       электронная почта
     * @param phoneNumber номер телефона
     * @param userInfo    детальная информация
     */
    public User(Long id, String lastName, String firstName, String middleName,
                LocalDate birthDate, String email, String phoneNumber, UserInfo userInfo) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userInfo = userInfo;
    }

    /**
     * Конструктор по умолчанию.
     */
    public User() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userDetailsInfo) {
        this.userInfo = userDetailsInfo;
    }
}