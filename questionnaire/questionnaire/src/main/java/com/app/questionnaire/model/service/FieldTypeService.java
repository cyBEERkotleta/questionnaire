package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.FieldType;
import com.app.questionnaire.model.repository.FieldTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * конкретный сервис для управления операциями
 * со списком типов полей
 *
 * @author Катя Левкович
 * @version 1.0, 16.07.2023
 */
@Service
@RequiredArgsConstructor
public class FieldTypeService implements IFieldTypeService {
    private final FieldTypeRepository fieldTypeRepository;

    @Override
    public List<FieldType> findAll() {
        return this.fieldTypeRepository.findAll();
    }

    @Override
    public FieldType getFieldTypeById(Short id) {
        return this.fieldTypeRepository.getFieldTypeById(id);
    }
}
