package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.Answer;
import com.app.questionnaire.model.entity.Field;
import com.app.questionnaire.model.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями
 * с полями форм
 *
 * @author Катя Левкович
 * @version 1.0, 29.06.2023
 */
@Service
@RequiredArgsConstructor
public class FieldService implements IFieldService {
    private final FieldRepository fieldRepository;

    @Override
    public Field getFieldById(Long id) {
        return fieldRepository.getFieldById(id);
    }

    @Override
    public List<Field> getFieldsByFormId(Long id) {
        return fieldRepository.getFieldsByFormId(id);
    }

    @Override
    public List<Field> getActiveFieldsByFormId(Long id) {
        return fieldRepository.getFieldsByFormIdAndActiveIsTrue(id);
    }

    @Override
    public void deleteFieldById(Long id) {
        fieldRepository.deleteById(id);
    }

    @Override
    public Field saveFieldAndCreateId(Field field) {
        return fieldRepository.save(field);
    }
}
