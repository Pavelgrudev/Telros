package ru.restapi.Telros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restapi.Telros.model.UserInfo;

/**
 * Расширяет интерфейс JpaRepository, предоставляя методы CRUD
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}