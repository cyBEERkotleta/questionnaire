package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.HashedPassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * доступ к хранилищу паролей пользователей
 * (таблица passwords)
 *
 * @author Катя Левкович
 * @version 1.0, 06.07.2023
 */
@Repository
public interface HashedPasswordRepository extends CrudRepository<HashedPassword, Long> {
}