package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.Form;
import com.app.questionnaire.model.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями
 * с формами (анкетами)
 *
 * @author Катя Левкович
 * @version 1.0, 29.06.2023
 */
@Service
@RequiredArgsConstructor
public class FormService implements IFormService {
    private final FormRepository formRepository;

    @Override
    public Form getFormById(Long id) {
        return formRepository.getFormById(id);
    }

    @Override
    public List<Form> getFormsByUserId(Long id) {
        return formRepository.getFormsByUserId(id);
    }

    @Override
    public List<Form> getFormsByTopicId(Long id) {
        return formRepository.getFormsByTopicId(id);
    }

    @Override
    public void deleteFormById(Long id) {
        formRepository.deleteById(id);
    }

    @Override
    public Form saveFormAndCreateId(Form form) {
        return formRepository.save(form);
    }
}