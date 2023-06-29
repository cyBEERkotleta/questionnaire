package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.Form;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу форм-опросников (анкет)
 * (таблица forms)
 *
 * @author Катя Левкович
 * @version 1.0, 26.06.2023
 */
@Repository
public interface FormRepository extends CrudRepository<Form, Long> {
    public Form getFormById(Long id);
    public List<Form> getFormsByTopicId(Long id);
    public List<Form> getFormsByUserId(Long id);
}