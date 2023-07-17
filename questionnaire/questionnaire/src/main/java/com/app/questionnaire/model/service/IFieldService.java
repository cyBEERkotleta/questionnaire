package com.app.questionnaire.model.service;

import com.app.questionnaire.exception.FieldException;
import com.app.questionnaire.model.entity.Field;

import java.util.List;

/**
 * абстрактный сервис для управления операциями
 * с полями форм
 *
 * @author Катя Левкович
 * @version 1.0, 29.06.2023
 */
public interface IFieldService {
    public Field getFieldById(Long id);
    public List<Field> getFieldsByFormId(Long id);
    public List<Field> getActiveFieldsByFormId(Long id);
    public void deleteFieldById(Long id);
    public Field saveField(Field field) throws FieldException;
}