package com.app.questionnaire.model.service;

import com.app.questionnaire.model.entity.FieldType;

import java.util.List;

/**
 * абстрактный сервис для управления операциями
 * со списком типов полей
 *
 * @author Катя Левкович
 * @version 1.0, 16.07.2023
 */
public interface IFieldTypeService {
    public List<FieldType> findAll();
    public FieldType getFieldTypeById(Short id);
}
