package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу типов полов пользователей
 *
 * @author Катя Левкович
 * @version 1.0, 07.07.2023
 */
@Repository
public interface GenderRepository extends CrudRepository<Gender, Short> {
    @Override
    public List<Gender> findAll();
}
