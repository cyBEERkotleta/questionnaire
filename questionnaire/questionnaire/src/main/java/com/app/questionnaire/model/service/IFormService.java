package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.FormException;
import com.app.questionnaire.model.entity.Form;

import java.util.List;

/**
 * абстрактный сервис для управления операциями
 * с формами (анкетами)
 *
 * @author Катя Левкович
 * @version 1.1, 29.06.2023
 */
public interface IFormService {
    public List<Form> findAll();
    public Form getFormById(Long id);
    public List<Form> getFormsByUserId(Long id);
    public List<Form> getFormsByTopicId(Long id);
    public void deleteFormById(Long id);
    public Form saveForm(Form form) throws FormException;
}