package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу ответов
 * (таблица answers)
 *
 * @author Катя Левкович
 * @version 1.1, 26.06.2023
 */
@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
    public Answer getAnswerById(Long id);
    public List<Answer> getAnswersByAnsweredFormId(Long id);
    public List<Answer> getAnswersByFieldId(Long id);
}