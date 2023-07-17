package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.FormException;
import com.app.questionnaire.model.entity.Form;
import com.app.questionnaire.model.repository.FormRepository;
import com.app.questionnaire.model.validator.FormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями
 * с формами (анкетами)
 *
 * @author Катя Левкович
 * @version 1.1, 29.06.2023
 */
@Service
@RequiredArgsConstructor
public class FormService implements IFormService {
    private final FormRepository formRepository;

    @Override
    public List<Form> findAll() {
        return formRepository.findAll();
    }

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
    public Form saveForm(Form form) throws FormException {
        FormValidator.getInstance().checkValidityOrThrown(form);

        return formRepository.save(form);
    }
}
