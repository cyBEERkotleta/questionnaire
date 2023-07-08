package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.AnsweredForm;

import java.util.List;

/**
 * абстрактный сервис для управления операциями
 * с отвеченными анкетами
 *
 * @author Катя Левкович
 * @version 1.0, 29.06.2023
 */
public interface IAnsweredFormService {
    public List<AnsweredForm> getAnsweredFormsByFormId(Long id);
    public AnsweredForm getAnsweredFormById(Long id);
    public void deleteAnsweredFormById(Long id);
    public AnsweredForm saveAnsweredForm(AnsweredForm answeredForm);
}