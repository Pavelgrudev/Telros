package ru.restapi.Telros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restapi.Telros.model.UserPhoto;

/**
 * Расширяет интерфейс JpaRepository, предоставляя методы CRUD
 */
@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {
}