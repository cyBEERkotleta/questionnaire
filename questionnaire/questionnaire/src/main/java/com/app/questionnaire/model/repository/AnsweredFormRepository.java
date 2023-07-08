package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.AnsweredForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу отвеченных анкет
 * (таблица answered_forms)
 *
 * @author Катя Левкович
 * @version 1.2, 26.06.2023
 */
@Repository
public interface AnsweredFormRepository extends CrudRepository<AnsweredForm, Long> {
    public AnsweredForm getAnsweredFormById(Long id);
    public List<AnsweredForm> getAnsweredFormsByFormId(Long id);
}