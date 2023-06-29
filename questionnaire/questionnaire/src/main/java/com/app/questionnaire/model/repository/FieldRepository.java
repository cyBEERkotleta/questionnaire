package com.app.questionnaire.model.repository;

import com.app.questionnaire.model.entity.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * доступ к хранилищу полей для ввода
 * (таблица fields)
 *
 * @author Катя Левкович
 * @version 1.0, 26.06.2023
 */
@Repository
public interface FieldRepository extends CrudRepository<Field, Long> {
    public Field getFieldById(Long id);
    public List<Field> getFieldsByFormId(Long formId);
    public List<Field> getFieldsByFormIdAndActiveIsTrue(Long formId);
}