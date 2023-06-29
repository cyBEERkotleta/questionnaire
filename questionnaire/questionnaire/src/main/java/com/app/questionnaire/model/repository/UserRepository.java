package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу пользователей
 * (таблица users)
 *
 * это невероятно, но реализовывать этот интерфейс не нужно, так как
 * реализация уже заключена там внутри,
 * этот JPA сводит меня с ума
 *
 * @author Катя Левкович
 * @version 1.0, 25.06.2023
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    public List<User> findAll();

    public User getUserById(Long id);
}
