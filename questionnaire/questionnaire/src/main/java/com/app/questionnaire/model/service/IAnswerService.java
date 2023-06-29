package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.Answer;

import java.util.List;

/**
 * абстрактный сервис для управления операциями
 * с ответами пользователей на поля форм
 *
 * @author Катя Левкович
 * @version 1.0, 29.06.2023
 */
public interface IAnswerService {
    public Answer getAnswerById(Long id);
    public List<Answer> getAnswersByAnsweredFormId(Long id);
    public void deleteAnswerById(Long id);
    public Answer saveAnswerAndCreateId(Answer answer);
}
